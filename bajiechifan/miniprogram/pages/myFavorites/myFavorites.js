// pages/myFavorites/myFavorrites.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    sort_type_str: ["按收藏时间排序", "按收藏时间排序", "按名称排序", "按名称排序", "按距离排序"],
    sort_type: 0,
    page_state: 0,
    types: [1, 1, 1],
    status: 0,
    distance: -1,
    temp: {
      types: [1, 1, 1],
      status: 0,
      distance: -1
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let _this = this;
    _this.load();
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

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  // 响应点击筛选按钮
  filter: function () {
    let _this = this;
    if (_this.data.page_state == 1) {
      _this.cancel();
      return;
    }
    _this.setData({
      page_state: 1
    });
  },

  // 选择类型
  filterType: function (e) {
    let _this = this;
    let type = e.currentTarget.dataset.type;
    _this.setData({
      ["types[" + type + "]"]: 1 - _this.data.types[type]
    });
  },

  // 选择类型
  filterStatus: function (e) {
    let _this = this;
    _this.setData({
      status: e.currentTarget.dataset.status
    });
  },

  // 筛选距离
  filterDistance: function (e) {
    let _this = this;
    _this.setData({
      distance: e.currentTarget.dataset.distance
    });
  },

  // 响应点击排序按钮
  sort: function () {
    let _this = this;
    if (_this.data.page_state == 2) {
      _this.cancel();
      return;
    }
    _this.setData({
      page_state: 2
    });
  },

  sortByTime: function () {
    let _this = this;
    _this.setData({
      sort_type: (_this.data.sort_type !== 0) ? 0 : 1,
      page_state: 0
    });
  },

  sortByName: function () {
    let _this = this;
    _this.setData({
      sort_type: (_this.data.sort_type !== 2) ? 2 : 3,
      page_state: 0
    });
  },

  sortByDistance: function () {
    let _this = this;
    _this.setData({
      sort_type: 4,
      page_state: 0
    });
  },

  // 取消
  cancel: function () {
    let _this = this;
    if (_this.data.page_state == 1) {
      _this.setData({
        types: JSON.parse(JSON.stringify(_this.data.temp.types)),
        status: _this.data.temp.status,
        distance: _this.data.temp.distance
      });
    }
    _this.setData({
      page_state: 0
    });
  },

  // 确定筛选
  submit: function () {
    let _this = this;
    if (_this.data.page_state == 1) {
      let temp = _this.data.temp;
      temp.types = JSON.parse(JSON.stringify(_this.data.types));
      temp.status = _this.data.status;
      temp.distance = _this.data.distance;
      _this.setData({
        temp: temp
      });
    }
    _this.setData({
      page_state: 0
    });
  },

  // 加载数据
  load: function() {
    let _this = this;
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/collection/list',
      method: "POST",
      header: {
        token: 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJvZXR2SjRnZGdxYjJ0Zm9xT3VRLWx5Q09SOEZnIn0.xkp0p3a_fpow-lZKs8AInmL4w53oXIYo8PG4X5F3cTY'
      },
      data: {
        types: _this.data.types,
        status: _this.data.status,
        distance: _this.data.distance,
        sortType: _this.data.sort_type,
        longitude: 0,
        latitude: 0
      },
      success: (res) => {
        console.log(res);
      }
    })
  }
})