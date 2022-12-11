Page({
  data: {
    canteen_list: [],
    collectFlagArray: [],
    swiperList: [],
    loc: '',
    longitude: 113.936696,
    latitude: 22.532742
  },
  onLoad() {
    let _this = this;
    let app = getApp();
    // wx.getSetting({
    // success(res) {
    // if (res.authSetting['scope.userLocation']) {
    wx.getLocation({
      success(res) {
        console.log(res, 'location');
        let location = {
          longitude: res.longitude,
          latitude: res.latitude
        };
        app.globalData.location = location;
        wx.setStorageSync('location', location);
        _this.getLocation();
      },
      fail() {
        console.log('fail');
        _this.getLocation();
      }
    })
    // }
    // else {
    // }
    // }
    // }
    // })
  },
  onShow() {
    var j;
    var temp = [];
    for (j = 0; j < this.data.canteen_list.length; j++) {
      temp[j] = false;
    }
    this.setData({
      collectFlagArray: temp
    })
    let _this = this;
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/canteen/list',
      method: 'POST',
      data: {
        "sortType": 0,
        "longitude": 8,
        "latitude": 82
      },
      header: {
        'token': getApp().globalData.token,
      },
      success(res) {
        _this.setData({
          canteen_list: res.data.data
        });
      },
      fail() {
        console.log("fail")
      }
    })
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/banner/list',
      method: 'GET',
      success(res) {
        var resstr = JSON.stringify(res.data)
        var resobj = JSON.parse(resstr)
        _this.setData({
          swiperList: resobj.data
        })
      },
      fail() {
        console.log("fail")
      }
    })
    this.getLocation()
  },
  toCanteenDetail(e) {
    console.log(e.currentTarget.dataset.id)
    wx.navigateTo({
      url: '/pages/canteenDetails/canteenDetails?id=' + e.currentTarget.dataset.id,
      // url: "/pages/canteen/canteen?id=" + e.currentTarget.dataset.id
    })
  },
  clickCollection: function (e) {
    let _this = this;
    let i = e.target.dataset.num;
    let token = getApp().globalData.token;
    // 如果用户未登录
    if (!token) {
      console.log("登录后才可进行收藏");
      return;
    }
    // 用户已登录
    _this.setData({
      ["canteen_list[" + i + "].isCollected"]: !_this.data.canteen_list[i].isCollected
    });
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/collection/',
      method: 'POST',
      header: {
        'token': token
      },
      data: {
        type: 0,
        targetId: _this.data.canteen_list[i].canteenId,
        isCollected: _this.data.canteen_list[i].isCollected ? 1 : 0
      },
      success: (res) => {
        if (res.data.code != 200) {
          console.log("失败（收藏）", res)
        }
        if (_this.data.canteen_list[i].isCollected) {
          console.log("收藏成功");
        } else {
          console.log("取消收藏成功");
        }
      }
    })
  },
  clickSearch() {
    wx.navigateTo({
      url: '/pages/search/search',
    })
  },
  toMyFavorites() {
    wx.navigateTo({
      url: '/pages/myFavorites/myFavorites',
    })
  },
  toChoosePosition() {
    wx.navigateTo({
      url: '/pages/choosePosition/choosePosition',
    })
  },
  getLocation() {
    let _this = this;
    let app = getApp();
    if (app.globalData.location) {
      location = app.globalData.location;
      if (location.name) {
        _this.setData({
          longitude: location.longitude,
          latitude: location.latitude,
          loc: location.name
        });
      } else {
        wx.request({
          url: `https://apis.map.qq.com/ws/geocoder/v1/?location=${location.latitude},${location.longitude}&key=EFYBZ-GFPKG-7DXQK-I2RD7-U5RW2-CSBPZ`,
          success(res) {
            console.log(res);
            location.name = res.data.result.formatted_addresses.recommend;
            _this.setData({
              longitude: location.longitude,
              latitude: location.latitude,
              loc: location.name
            });
            app.globalData.location = location;
            wx.setStorageSync('location', location);
          }
        });
      }
    } else {
      _this.setData({
        longitude: 113.936696,
        latitude: 22.532742,
        loc: "深圳大学（粤海校区）"
      });
    }
  },
  toMap() {
    wx.navigateTo({
      url: '/pages/map/map',
    })
  }
})