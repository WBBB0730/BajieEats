Page({
  data:{
    commentList:[],
    avatarUrl:wx.getStorageSync('userInfo').avatarUrl,
    nickName:wx.getStorageSync('userInfo').nickName
  },
  onShow(){
    let app = getApp();
    var _this = this;
    //获取用户评价列表
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/comment/user',
      method: 'GET',
      header: {
        'token': app.globalData.token
      },
      success(res) {
        var resstr = JSON.stringify(res.data)
        var resobj = JSON.parse(resstr)
        console.log(resobj)
        _this.setData({
          commentList: resobj.data
        })
      },
      fail(){
        console.log(0);
      }
    })
    //截取时间
    for(let i = 0;i < this.data.commentList.length;i++){
      var temp ='commentList['+i+'].createTime';
      var change = this.data.commentList[i].createTime.substring(0,10);
      this.setData({
        [temp]:change
      })
    }
  },
  //点击更多图标
  more(e){
    let i = e.currentTarget.dataset.id;
    let _this = this;
    console.log(i,getApp().globalData.token);
    wx.showActionSheet({
      itemList: ['分享','删除'],
      itemColor: '#007aff',
      success(res) {
        if(res.tapIndex === 0){
          
        }else if(res.tapIndex === 1){
          wx.showModal({
            title:'确定删除这条内容吗',
            success(res){
              //点击确认，调用删除接口
              if(res.confirm){
                wx.request({
                  url: 'http://114.132.234.161:8888/bajie/comment',
                  method:'DELETE',
                  data: {
                    commentId: i
                  },
                  header: {
                    'content-type':'application/x-www-form-urlencoded',
                    'token': getApp().globalData.token
                  },
                  success(res){
                    //如果删除成功再次调用接口重载界面
                    console.log(res)
                    wx.request({
                      url: 'http://114.132.234.161:8888/bajie/comment/user',
                      method: 'GET',
                      header: {
                        'token': getApp().globalData.token
                      },
                      success(res) {
                        var resstr = JSON.stringify(res.data)
                        var resobj = JSON.parse(resstr)
                        console.log(resobj)
                        _this.setData({
                          commentList: resobj.data
                        })
                      },
                      fail(){
                        console.log(0);
                      }
                    })
                    console.log('load')
                  }
                })
              }
              else{
                console.log('未删除')
              }
            }
          })
        }
      }
    })
  },
  toDish(e){
    let i = e.currentTarget.dataset.dishid;
    console.log(i,'dishid');
    wx.navigateTo({
      url: '/pages/dish/dish?id=' + i,
    })
  },
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
})