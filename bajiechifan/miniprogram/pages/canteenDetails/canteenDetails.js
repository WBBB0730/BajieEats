// pages/canteenDetails/canteenDetails.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    canteenId: null,
    keyWord: null,
    canteenInfo: null,
    floorList: null,
    floorIndex: 0,
    windowIndex: 0,
    windowHeight: 0,
    scrollTop: 0,
    contentTop: 2e9,
    corner: 0,
    blur: 0,
    dots: true,
    autoplay: true,
    scrollEnabled: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let _this = this;
    _this.setData({
      canteenId: options.id,
      keyWord: options.keyWord ? JSON.parse(decodeURIComponent(options.keyWord)) : null
    });
  },

  /**
   * 生命周期函数--监听页面显示
   */
  async onShow() {
    let _this = this;
    let app = getApp();
    let res;

    // 获取餐厅信息
    res = await app.request({
      url: "/canteen/",
      header: {
        token: app.globalData.token
      },
      data: {
        canteenId: _this.data.canteenId,
        longitude: app.globalData.location ? app.globalData.location.longitude : 0,
        latitude: app.globalData.location ? app.globalData.location.latitude : 0
      }
    });
    // 获取成功
    if (res.data.code == 200) {
      let canteenInfo = res.data.data;
      canteenInfo.images = [canteenInfo.mainImage, ...canteenInfo.canteenUrls];
      _this.setData({
        canteenInfo: canteenInfo
      });
      console.log(canteenInfo);
    } else {
      app.showErr(res.data.message);
    }

    // 获取楼层列表
    res = await app.request({
      url: "/canteen/getFloorList",
      header: {
        token: app.globalData.token
      },
      data: {
        canteenId: _this.data.canteenId,
        keyWord: _this.data.keyWord
      }
    });
    // 获取成功
    if (res.data.code == 200) {
      _this.setData({
        floorList: res.data.data
      });
    } else {
      app.showErr(res.data.message);
    }

    _this.setData({
      windowHeight: toRpx(wx.getSystemInfoSync().windowHeight)
    })

    // 获取内容部分顶部坐标
    wx.createSelectorQuery()
      .select(".content").boundingClientRect()
      .selectViewport().scrollOffset()
      .exec(function (res) {
        _this.setData({
          contentTop: res[0].top + res[1].scrollTop
        })
      });

    // 禁用滚动
    if (_this.data.floorList[_this.data.floorIndex].navigate == "搜索结果") {
      wx.createSelectorQuery()
        .select(".result").node()
        .exec(function (res) {
          res[0].node.scrollEnabled = false;
          _this.setData({
            scrollEnabled: false
          })
        });
    } else {
      wx.createSelectorQuery()
        .select(".dish-list").node()
        .exec(function (res) {
          res[0].node.scrollEnabled = false;
          _this.setData({
            scrollEnabled: false
          })
        });
    }

    // 生成滚动位置信息
    if (_this.data.floorList[_this.data.floorIndex].navigate == "搜索结果") {

    } else {
      for (let i = 0; i < this.data.floorList[this.data.floorIndex].objects.length; i++) {
        let window = this.data.floorList[this.data.floorIndex].objects[i];
        this.setData({
          window_top_list: []
        });
        wx.createSelectorQuery()
          .select("#window-" + i).boundingClientRect()
          .select(".dish-list").boundingClientRect()
          .select(".dish-list").scrollOffset()
          .exec(res => {
            let obj = {
              index: i,
              top: res[0].top - res[1].top + res[2].scrollTop
            };
            _this.data.window_top_list.push(obj);
            // console.log(_this.data.window_top_list);
          })
      };
    }
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    wx.stopPullDownRefresh();
  },

  /**
   * 用户滚动页面
   */
  onPageScroll: function (e) {
    let _this = this;

    // 滚动到顶部时显示圆点并开始自动播放
    if (e.scrollTop <= 0) {
      e.scrollTop = 0;
      this.setData({
        dots: true,
        autoplay: true
      });
    }

    // 不在顶部时隐藏圆点并停止自动播放
    if (e.scrollTop > 0) {
      this.setData({
        dots: false,
        autoplay: false
      });
    }

    // 平滑圆角过渡
    if (e.scrollTop >= 0 && e.scrollTop <= toPx(120)) {
      this.setData({
        corner: toRpx(e.scrollTop / 3)
      });
    }

    // 平滑模糊效果
    if (e.scrollTop >= 0 && e.scrollTop <= toPx(240)) {
      _this.setData({
        blur: e.scrollTop / 20
      })
    }

    // 页面到达底部后解锁列表滚动
    if (this.data.scrollEnabled === false && e.scrollTop >= this.data.scrollTop && e.scrollTop >= this.data.contentTop) {
      wx.createSelectorQuery()
        .select(_this.data.floorList[_this.data.floorIndex].navigate == "搜索结果" ? ".result" : ".dish-list").node()
        .exec(function (res) {
          res[0].node.scrollEnabled = true;
          _this.setData({
            scrollEnabled: true
          })
          console.log("触底");
        });
    }

    // 页面离开底部后锁定列表滚动
    if (this.data.scrollEnabled === true && e.scrollTop < this.data.scrollTop && e.scrollTop < this.data.contentTop) {
      wx.createSelectorQuery()
        .select(_this.data.floorList[_this.data.floorIndex].navigate == "搜索结果" ? ".result" : ".dish-list").node()
        .exec(function (res) {
          res[0].node.scrollEnabled = false;
          _this.setData({
            scrollEnabled: false
          })
          console.log("上滑");
        });
    }

    // 更新页面位置
    setTimeout(function () {
      _this.setData({
        scrollTop: e.scrollTop
      })
    }, 0);
  },

  // 选择楼层
  selectFloor: function (e) {
    let _this = this;
    let index = e.currentTarget.dataset.index;
    _this.setData({
      floorIndex: index,
      selected_windowIndex: 0
    });
    wx.pageScrollTo({
      scrollTop: this.data.contentTop,
    })

    if (_this.data.floorList[index].navigate == "搜索结果") {

    } else {
      wx.createSelectorQuery()
        .select(".dish-list").node()
        .exec((res) => {
          const scroll_view = res[0].node;
          scroll_view.scrollIntoView("#window-0");
          console.log("选择:" + scroll_view.scrollEnabled);
        });

      // 生成滚动位置信息
      for (let i = 0; i < this.data.floorList[this.data.floorIndex].objects.length; i++) {
        let window = this.data.floorList[this.data.floorIndex].objects[i],
          _top;
        this.setData({
          window_top_list: []
        });
        wx.createSelectorQuery()
          .select("#window-" + i).boundingClientRect()
          .select(".dish-list").boundingClientRect()
          .select(".dish-list").scrollOffset()
          .exec(res => {
            let obj = {
              index: window.windowIndex,
              top: res[0].top - res[1].top + res[2].scrollTop
            };
            _this.data.window_top_list.push(obj);
            // console.log(_this.data.window_top_list);
          })
      };
    }
  },

  // 选择窗口
  selectWindow: function (e) {
    wx.pageScrollTo({
      scrollTop: this.data.contentTop,
    })
    wx.createSelectorQuery()
      .select(".dish-list").node()
      .exec((res) => {
        const scroll_view = res[0].node;
        scroll_view.scrollIntoView("#window-" + e.currentTarget.dataset.id);
        console.log("选择:" + scroll_view.scrollEnabled);
      });
    this.setData({
      selected_windowIndex: e.currentTarget.dataset.index
    });
  },

  // 点击餐厅收藏按钮
  favorCanteen: function () {
    let _this = this;
    let token = getApp().globalData.token;
    // 如果用户未登录
    if (!token) {
      console.log("登录后才可进行收藏");
      return;
    }
    // 用户已登录
    _this.setData({
      "canteenInfo.isCollected": !_this.data.canteenInfo.isCollected
    });
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/collection/',
      method: 'POST',
      header: {
        'token': token
      },
      data: {
        type: 0,
        targetId: _this.data.canteenId,
        isCollected: _this.data.canteenInfo.isCollected ? 1 : 0
      },
      success: (res) => {
        console.log(res);
        if (res.data.code == 200) {
          if (_this.data.canteenInfo.isCollected) {
            console.log("收藏成功");
          } else {
            console.log("取消收藏成功");
          }
        } else {
          console.log("失败（收藏）", res);
          getApp().showErr(res.data.message);
        }
      }
    })
  },

  // 点击窗口收藏按钮
  favorWindow: function (e) {
    let _this = this;
    let index = e.currentTarget.dataset.index;
    let window = _this.data.floorList[_this.data.floorIndex].objects[index];
    console.log(e, window);
    let token = getApp().globalData.token;
    // 如果用户未登录
    if (!token) {
      console.log("登录后才可进行收藏");
      return;
    }
    // 用户已登录
    window.isCollected = !window.isCollected;
    _this.setData({
      ["floorList[" + _this.data.floorIndex + "].objects[" + index + "]"]: window
    });
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/collection/',
      method: 'POST',
      header: {
        'token': token
      },
      data: {
        type: 1,
        targetId: window.winId,
        isCollected: window.isCollected ? 1 : 0
      },
      success: (res) => {
        console.log(res);
        if (res.data.code == 200) {
          if (_this.data.canteenInfo.isCollected) {
            console.log("收藏成功");
          } else {
            console.log("取消收藏成功");
          }
        } else {
          console.log("失败（收藏）", res)
          getApp().showErr(res.data.message);
        }
      }
    })
  },

  // 滚动菜品列表
  scrollDishList: function (e) {
    // console.log(e.detail.scrollTop);
    let length = this.data.window_top_list.length;
    for (let i = 0; i < length; i++) {
      if (e.detail.scrollTop > this.data.window_top_list[i].top + toPx(5) && (i == length - 1 || e.detail.scrollTop < this.data.window_top_list[i + 1].top - toPx(5)) && this.data.window_top_list[i].index !== this.data.selected_windowIndex) {
        this.setData({
          selected_windowIndex: this.data.window_top_list[i].index
        });
        break;
      }
    }
  },
  //跳转
  toDish: function (e) {
    wx.navigateTo({
      url: '/pages/dish/dish?id=' + e.currentTarget.dataset.id,
    })
  },

})

function toPx(rpx) {
  return rpx / 750 * wx.getSystemInfoSync().windowWidth;
}

function toRpx(px) {
  return px / wx.getSystemInfoSync().windowWidth * 750;
}