Page({
  data: {
    dishId: 0,
    dishName: '',
    dishImage: '',
    dishType: '大众菜',
    price: 0,
    tags: '',
    ingredient: '',
    score: 0,
    labelList: [{
      "name": "咸鲜"
    }, {
      "name": "大众菜"
    }],
    commentList: [],
    commentUrls: [],
    commentListNum: 0,
    collectionFlag: false,
    dialogFlag: false,
    // cardType为0则为详情，为1则为评价
    cardType: 0,
    commentType: null,
  },

  onLoad(options) {
    this.setData({
      dishId: options.id
    })
  },

  onShow() {
    var _this = this;
    //菜品详情信息
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/dish/',
      method: 'GET',
      data: {
        dishId: _this.data.dishId
      },
      header: {
        'contebt-Type': 'application/json',
        'token': getApp().globalData.token
      },
      success(res) {
        var resstr = JSON.stringify(res.data)
        var resobj = JSON.parse(resstr)
        _this.setData({
          dishId: resobj.data.dishId,
          dishName: resobj.data.dishName,
          dishImage: resobj.data.dishImage,
          price: resobj.data.price,
          score: resobj.data.score,
          ingredient: resobj.data.ingredients,
          tags: resobj.data.tags,
          collectionFlag: resobj.data.isCollected
        })
        console.log(resobj)
      },
      fail() {
        console.log("fail")
      }
    })
    //   评价接口
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/comment/list',
      method: 'GET',
      data: {
        dishId: _this.data.dishId,
        pageIndex: 1,
        pageSize: 10
      },
      header: {
        'contebt-Type': 'application/json'
      },
      success(res) {
        var resstr = JSON.stringify(res.data)
        var resobj = JSON.parse(resstr)
        _this.setData({
          commentList: resobj.data.list,
          commentListNum: resobj.data.list.length
        })
        console.log(resobj, 'comment')
      },
      fail() {
        console.log("fail")
      }
    })
  },
  clickCollectionIcon(e) {
    let _this = this;
    let token = getApp().globalData.token;
    // 如果用户未登录
    if (!token) {
      console.log("登录后才可进行收藏");
      return;
    }
    // 用户已登录
    _this.setData({
      collectionFlag: !_this.data.collectionFlag
    });
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/collection/',
      method: 'POST',
      header: {
        'token': token
      },
      data: {
        type: 2,
        targetId: _this.data.dishId,
        isCollected: _this.data.collectionFlag ? 1 : 0
      },
      success: (res) => {
        console.log(res);
        if (res.data.code != 200) {
          console.log("失败（收藏）", res)
        }
        if (_this.data.collectionFlag) {
          console.log("收藏成功");
        } else {
          console.log("取消收藏成功");
        }
      }
    })
  },
  //切换至详情卡片
  toDetail(e) {
    this.setData({
      cardType: 0
    })
  },
  //切换至评价卡片
  toComment(e) {
    this.setData({
      cardType: 1
    })
  },
  // 评价按钮
  toGood(e) {
    this.setData({
      commentType: 'Good'
    })
  },
  toMiddle(e) {
    this.setData({
      commentType: 'Middle'
    })
  },
  toBad(e) {
    this.setData({
      commentType: 'Bad'
    })
  },
  selectDialog(e) {
    if (this.data.dialogFlag) {
      this.setData({
        dialogFlag: false
      })
    } else {
      this.setData({
        dialogFlag: true
      })
    }
  },
  cancelDialog(e) {
    this.setData({
      dialogFlag: false
    })
  },
  //发表评价
  toPost() {
    let _this = this;
    let app = getApp();
    console.log(app.globalData.token, 1);
    if (app.globalData.token) {
      wx.navigateTo({
        url: '/pages/postComment/postComment?id=' + _this.data.dishId,
      })
    } else {
      wx.showModal({
        title: '提示',
        content: '请先授权登录~',
        success(res) {
          if (res.confirm) {
            wx.showLoading({
              title: '正在登录',
              mask: true
            })
            app.login().then(wx.showToast({
              title: '登录成功',
            }));
          } else {
            console.log('stay')
          }
        }
      })
    }
  },
  //点击看大图
  bigImg(e) {
    let i = e.currentTarget.dataset.index;
    console.log(i,'photoIndex');
    let j = e.currentTarget.dataset.commentindex;
    console.log(j,'commentIndex');
    let commentUrls = this.data.commentList[j].commentUrls;
    console.log(commentUrls);
    wx.previewImage({
      current: commentUrls[i],
      urls: commentUrls
    })
  }
});