Page({
  data:{
    longitude:0,
    latitude:0,
    markers:[],
    distance: [],
    loc:'',
    name:'',
    s:'',
    markerId: -1,
    showCanteenInfo: false,
    canteenId: -1
  },
  onShow(){
    let _this = this;
    let app = getApp();
    let location = app.globalData.location;
    console.log(location);
    _this.setData({
      longitude: location.longitude,
      latitude: location.latitude,
      markers:[
        {
          id: 0,
          longitude: 0,
          latitude: 0,
          iconPath:'/images/my_location@3x .png',
          width: 30,
          height: 30
        },
        {
          id: 1,
          width: 0,
          height: 0, 
          longitude: 113.93858,
          latitude: 22.53409,
          customCallout: {
            display: 'ALWAYS',
            anchorY: -5
          }
        },
        {
          id: 2,
          width: 0,
          height: 0, 
          longitude: 113.940782,
          latitude: 22.530134,
          customCallout: {
            display: 'ALWAYS',
            anchorY: -5
          }
        },
        {
          id: 3,
          width: 0,
          height: 0, 
          longitude: 113.9341,
          latitude: 22.53435,
          customCallout: {
            display: 'ALWAYS',
            anchorY: -5
          }
        }
      ]
    })
    _this.getDistanceArray()
  },
  rad(d){
    return d*Math.PI / 180;
  },
  getDistance(canteenl,canteena){
    let location = wx.getStorageSync('location');
    let myl = location.longitude;
    let mya = location.latitude;
    let a = Math.abs(this.rad(myl) - this.rad(canteenl));
    let b = Math.abs(this.rad(mya) - this.rad(canteena));
    var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
    Math.cos(this.rad(myl)) * Math.cos(this.rad(canteenl)) * Math.pow(Math.sin(b / 2), 2)));
    s = s * 6378.137; // EARTH_RADIUS;
    s = Math.round(s * 10) / 10; //输出为公里
    if(s < 1) {
      s = s * 1000;
      return s + 'm';
    }
    return s + 'km'
  },
  getDistanceArray(){
    let distance = new Array(this.data.markers.length);
    for(let i = 1; i < this.data.markers.length; i++){
      distance[i] = this.getDistance(this.data.markers[i].longitude,this.data.markers[i].latitude);
      console.log(distance[i])
    }
    this.setData({
      distance: distance
    })
  },
  showInfo(e){
    let that = this;
    console.log(e.detail,e);
    this.setData({
      showCanteenInfo:true
    })
    if(e.detail.markerId == 1){
      let l = this.data.markers[1].longitude;
      let a = this.data.markers[1].latitude;
      let s = this.data.distance[1];
      wx.request({
        url: `https://apis.map.qq.com/ws/geocoder/v1/?location=${a},${l}&key=EFYBZ-GFPKG-7DXQK-I2RD7-U5RW2-CSBPZ`,
        success(res){
          console.log(res,1)
          that.setData({
            name:"实验餐厅",
            loc: res.data.result.address,
            s: s,
            markerId: 1,
            canteenId:463815153733600
          })
        }
      })
    }
    else if(e.detail.markerId == 2){
     let l = this.data.markers[2].longitude;
     let a = this.data.markers[2].latitude;
     let s = this.data.distance[2];
      wx.request({
        url: `https://apis.map.qq.com/ws/geocoder/v1/?location=${a},${l}&key=EFYBZ-GFPKG-7DXQK-I2RD7-U5RW2-CSBPZ`,
        success(res){
          console.log(res,1)
          that.setData({
            name:"荔园美食汇",
            loc: res.data.result.address,
            s: s,
            markerId: 2,
            canteenId: 7588696299405312
          })
        }
      })
    }
    else if(e.detail.markerId == 3){
      let l = this.data.markers[3].longitude;
      let a = this.data.markers[3].latitude;
      let s = this.data.distance[3];
      wx.request({
        url: `https://apis.map.qq.com/ws/geocoder/v1/?location=${a},${l}&key=EFYBZ-GFPKG-7DXQK-I2RD7-U5RW2-CSBPZ`,
        success(res){
          console.log(res,1)
          that.setData({
            name:"新西南餐厅",
            loc: res.data.result.address,
            s: s,
            markerId: 3,
            canteenId: 462252704161800
          })
        }
      })
    }
  },
  goHere(e){
    let id = e.currentTarget.dataset.markerid;
    let that = this;
    console.log(id,'id');
    wx.openLocation({
      latitude:that.data.markers[id].latitude,
      longitude:that.data.markers[id].longitude,
      name: that.data.name,
      address: that.data.loc,
      scale: 18
    })
  },
  toCanteen(){
    let id = this.data.canteenId
    console.log(id)
  wx.navigateTo({
      url: '/pages/canteenDetails/canteenDetails?id=' + id,
    })
  }
})