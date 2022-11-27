// pages/canteenDetails/canteenDetails.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    state: "state-1",
    scrollTop: 0,
    canteen_info: {
      canteen_id: "",
      canteen_images: [
        // 'cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/IMG_20221107_165024.jpg', 'cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/IMG_20221107_162531.jpg', 'cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/IMG_20221107_162419.jpg'
      ],
      is_canteen_favored: false,
      canteen_name: "沧海餐厅（南区食堂）",
      canteen_business_state: "营业中",
      canteen_business_hours: "07:00-08:30、10:30-13:00、16:00-21:00",
      canteen_distance: "900km",
      canteen_address: "深圳大学-沧海校区宿舍区附近ewfdsfsfsfwfwfe",
    },
    floor_list: [{
      floorIndex: 0,
      floorName: "1F",
      windowList: [{
        windowIndex: 0,
        windowId: 0,
        windowName: "石锅菜",
        openingSataus: "营业中",
        isCollected: false,
        dishList: [{
          dishName: "香芋排骨煲",
          price: "13",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/香芋排骨煲1：1.jpg",
          score: "4.7",
          tags: ["咸鲜", "不辣"]
        }, {
          dishName: "香芋排骨煲",
          price: "13",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/香芋排骨煲1：1.jpg",
          score: "4.7",
          tags: ["咸鲜", "不辣"]
        }, {
          dishName: "香芋排骨煲",
          price: "13",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/香芋排骨煲1：1.jpg",
          score: "4.7",
          tags: ["咸鲜", "不辣"]
        }, {
          dishName: "香芋排骨煲",
          price: "13",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/香芋排骨煲1：1.jpg",
          score: "4.7",
          tags: ["咸鲜", "不辣"]
        }, {
          dishName: "香芋排骨煲",
          price: "13",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/香芋排骨煲1：1.jpg",
          score: "4.7",
          tags: ["咸鲜", "不辣"]
        }, {
          dishName: "香芋排骨煲",
          price: "13",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/香芋排骨煲1：1.jpg",
          score: "4.7",
          tags: ["咸鲜", "不辣"]
        }]
      }, {
        windowId: 1,
        windowName: "大众菜、测试测试1212",
        openingSataus: "营业中",
        dishList: [{
          dishName: "番茄炒蛋",
          price: "3.5",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/番茄炒蛋1：1.jpg",
          score: "4.5",
          tags: ["酸甜", "不辣"]
        }, {
          dishName: "番茄炒蛋",
          price: "3.5",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/番茄炒蛋1：1.jpg",
          score: "4.5",
          tags: ["酸甜", "不辣"]
        }, {
          dishName: "番茄炒蛋",
          price: "3.5",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/番茄炒蛋1：1.jpg",
          score: "4.5",
          tags: ["酸甜", "不辣"]
        }, {
          dishName: "番茄炒蛋",
          price: "3.5",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/番茄炒蛋1：1.jpg",
          score: "4.5",
          tags: ["酸甜", "不辣"]
        }, {
          dishName: "番茄炒蛋",
          price: "3.5",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/番茄炒蛋1：1.jpg",
          score: "4.5",
          tags: ["酸甜", "不辣"]
        }, {
          dishName: "番茄炒蛋",
          price: "3.5",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/番茄炒蛋1：1.jpg",
          score: "4.5",
          tags: ["酸甜", "不辣"]
        }, {
          dishName: "番茄炒蛋",
          price: "3.5",
          dishImage: "cloud://cloud1-2gef5vhe7ba352bb.636c-cloud1-2gef5vhe7ba352bb-1314850831/images/番茄炒蛋1：1.jpg",
          score: "4.5",
          tags: ["酸甜", "不辣"]
        }]
      }]
    }, {
      floorIndex: 1,
      floorName: "2F"
    }],
    window_top_list: [],
    selected_floorIndex: 0,
    selected_windowIndex: 0,
    body_border_radius: 0,
    canteen_image_blur: 0,
    indicator_dots: true,
    autoplay: true,
    window_height: 0,
    scroll_enabled: false,
    content_top: 10000,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let _this = this;
    _this.setData({
      "canteen_info.canteen_id": options.id
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    let _this = this;
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/canteen/',
      data: {
        canteenId: _this.data.canteen_info.canteen_id
      },
      header: {
        'contetnt-type': 'application/json',
        'token': getApp().globalData.token
      },
      success: (res) => {
        let data = res.data.data;
        let temp = this.data.canteen_info;
        temp.canteen_name = data.canteenName;
        temp.is_canteen_favored = data.isCollected;
        temp.canteen_address = data.canteenAddress;
        temp.canteen_business_hours = data.openingTime;
        temp.canteen_business_state = data.isOpening;
        temp.canteen_images = [];
        temp.canteen_images.push(data.mainImage);
        for (let i = 0; i < data.canteenUrls.length; i++) {
          temp.canteen_images.push(data.canteenUrls[i].url);
        }
        _this.setData({
          canteen_info: temp
        })
      }
    })

    wx.request({
      url: 'http://114.132.234.161:8888/bajie/canteen/getFloorList',
      data: {
        canteenId: _this.data.canteen_info.canteen_id
      },
      header: {
        'contetnt-type': 'application/json',
        'token': getApp().globalData.token
      },
      success: (res) => {
        let temp = res.data.data;
        for (let i = 0; i < temp.length; i++) {
          temp[i].floorIndex = i;
          for (let j = 0; j < temp[i].windowList.length; j++) {
            temp[i].windowList[j].windowIndex = j;
          }
        }
        _this.setData({
          floor_list: temp
        });
      }
    });

    _this.setData({
      window_height: px_to_rpx(wx.getSystemInfoSync().windowHeight)
    })

    // 获取内容部分顶部坐标
    wx.createSelectorQuery()
      .select(".content").boundingClientRect()
      .selectViewport().scrollOffset()
      .exec(function (res) {
        _this.setData({
          content_top: res[0].top + res[1].scrollTop
        })
      });

    // 禁用滚动
    wx.createSelectorQuery()
      .select(".dish-list").node()
      .exec(function (res) {
        const scroll_view = res[0].node;
        scroll_view.scrollEnabled = false;
        _this.setData({
          scroll_enabled: false
        })
      });

    // 生成滚动位置信息
    for (let i = 0; i < this.data.floor_list[this.data.selected_floorIndex].windowList.length; i++) {
      let window = this.data.floor_list[this.data.selected_floorIndex].windowList[i],
        _top;
      this.setData({
        window_top_list: []
      });
      wx.createSelectorQuery()
        .select("#window-" + window.windowId).boundingClientRect()
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
        indicator_dots: true,
        autoplay: true
      });
    }

    // 不在顶部时隐藏圆点并停止自动播放
    if (e.scrollTop > 0) {
      this.setData({
        indicator_dots: false,
        autoplay: false
      });
    }

    // 平滑圆角过渡
    if (e.scrollTop >= 0 && e.scrollTop <= rpx_to_px(120)) {
      this.setData({
        body_border_radius: px_to_rpx(e.scrollTop / 3),
        // canteen_image_blur: e.scrollTop / 12
      });
    }

    // 平滑模糊效果
    if (e.scrollTop >= 0 && e.scrollTop <= rpx_to_px(240)) {
      _this.setData({
        canteen_image_blur: e.scrollTop / 20
      })
    }

    // 页面到达底部后解锁列表滚动
    if (this.data.scroll_enabled === false && e.scrollTop >= this.data.scrollTop && e.scrollTop >= this.data.content_top) {
      wx.createSelectorQuery()
        .select(".dish-list").node()
        .exec(function (res) {
          const scroll_view = res[0].node;
          scroll_view.scrollEnabled = true;
          _this.setData({
            scroll_enabled: true
          })
          console.log("触底：" + scroll_view.scrollEnabled);
        });
    }

    // 页面离开底部后锁定列表滚动
    if (this.data.scroll_enabled === true && e.scrollTop < this.data.scrollTop && e.scrollTop < this.data.content_top) {
      wx.createSelectorQuery()
        .select(".dish-list").node()
        .exec(function (res) {
          const scroll_view = res[0].node;
          scroll_view.scrollEnabled = false;
          _this.setData({
            scroll_enabled: false
          })
          console.log("上滑：" + scroll_view.scrollEnabled);
        });
    }

    // 更新页面位置
    setTimeout(function () {
      _this.setData({
        scrollTop: e.scrollTop
      })
    }, 0)
  },

  // 选择楼层
  selectFloor: function (e) {
    let _this = this;
    let floor_index = e.currentTarget.dataset.index;
    let window = _this.data.floor_list[floor_index].windowList[0];
    _this.setData({
      selected_floorIndex: floor_index,
      selected_windowIndex: 0
    });
    wx.pageScrollTo({
      scrollTop: this.data.content_top,
    })
    wx.createSelectorQuery()
      .select(".dish-list").node()
      .exec((res) => {
        const scroll_view = res[0].node;
        scroll_view.scrollIntoView("#window-0");
        console.log("选择:" + scroll_view.scrollEnabled);
      });

    // 生成滚动位置信息
    for (let i = 0; i < this.data.floor_list[this.data.selected_floorIndex].windowList.length; i++) {
      let window = this.data.floor_list[this.data.selected_floorIndex].windowList[i],
        _top;
      this.setData({
        window_top_list: []
      });
      wx.createSelectorQuery()
        .select("#window-" + window.windowId).boundingClientRect()
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
  },

  // 选择窗口
  selectWindow: function (e) {
    wx.pageScrollTo({
      scrollTop: this.data.content_top,
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
      "canteen_info.is_canteen_favored": !_this.data.canteen_info.is_canteen_favored
    });
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/collection/',
      method: 'POST',
      header: {
        'token': token
      },
      data: {
        type: 0,
        targetId: _this.data.canteen_info.canteen_id,
        isCollected: _this.data.canteen_info.is_canteen_favored ? 1 : 0
      },
      success: (res) => {
        console.log(res);
        if (res.statusCode != 200) {
          console.log("失败（收藏）", res)
        }
        if (_this.data.canteen_info.is_canteen_favored) {
          console.log("收藏成功");
        } else {
          console.log("取消收藏成功");
        }
      }
    })
  },

  // 点击窗口收藏按钮
  favorWindow: function (e) {
    let _this = this;
    let floor_index = e.currentTarget.dataset.floor_index;
    let window_index = e.currentTarget.dataset.window_index;
    let window = _this.data.floor_list[floor_index].windowList[window_index];
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
      ["floor_list[" + floor_index + "].windowList[" + window_index + "]"]: window
    });
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/collection/',
      method: 'POST',
      header: {
        'token': token
      },
      data: {
        type: 1,
        targetId: window.windowId,
        isCollected: window.isCollected ? 1 : 0
      },
      success: (res) => {
        console.log(res);
        if (res.statusCode != 200) {
          console.log("失败（收藏）", res)
        }
        if (_this.data.canteen_info.is_canteen_favored) {
          console.log("收藏成功");
        } else {
          console.log("取消收藏成功");
        }
      }
    })
  },

  // 滚动菜品列表
  scrollDishList: function (e) {
    // console.log(e.detail.scrollTop);
    let length = this.data.window_top_list.length;
    for (let i = 0; i < length; i++) {
      if (e.detail.scrollTop > this.data.window_top_list[i].top + rpx_to_px(5) && (i == length - 1 || e.detail.scrollTop < this.data.window_top_list[i + 1].top - rpx_to_px(5)) && this.data.window_top_list[i].index !== this.data.selected_windowIndex) {
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

function rpx_to_px(rpx) {
  return rpx / 750 * wx.getSystemInfoSync().windowWidth;
}

function px_to_rpx(px) {
  return px / wx.getSystemInfoSync().windowWidth * 750;
}