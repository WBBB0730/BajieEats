Page({
  data:{
    canteen_list:[],
    collectFlagArray:[],
    swiperList:[]
  },
  onLoad(){
    var j;
    var temp=[];
    for(j = 0;j<this.data.canteen_list.length;j++){
      temp[j] = false;
    }
    this.setData({
      collectFlagArray: temp
    })
    var _this = this;
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/canteen/list',
      method: 'POST',
      data: {
        "pageIndex": "1",
        "pageSize": "10",
        "sortType": "score",
        "longitude": 8,
        "latitude": 82
        },
      header: {
        'contebt-Type':'application/x-www-form-urlencoded' 
      },
      success(res){
        var resstr=JSON.stringify(res.data)
        var resobj=JSON.parse(resstr)
        _this.setData({
          canteen_list:resobj.data
        })
        console.log(resobj)
      },
      fail(){
        console.log("fail")
      }
    })
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/banner/list',
      method: 'GET',
      header: {
        'contebt-Type':'application/json'
      },
      success(res){
        var resstr=JSON.stringify(res.data)
        var resobj=JSON.parse(resstr)
        _this.setData({
          swiperList:resobj.data
        })
        console.log(resobj)
      },
      fail(){
        console.log("fail")
      }
    })
  },
  toCanteenDetail(){
    wx.navigateTo({
      url: '/pages/canteenDetails/canteenDetails',
    })
  },
  clickCollection: function (e){
    console.log(e.target.dataset.num);
    var i;
    i=e.target.dataset.num;
    console.log(i,"i");
    console.log(this.data.collectFlagArray);
    this.setData({
      ['collectFlagArray['+i+']']:!this.data.collectFlagArray[i]
    })
    console.log(this.data.collectFlagArray);
    console.log(this.data.collectFlagArray[i],i)
  },
  clickSearch(){
    wx.navigateTo({
      url: '/pages/search/search',
    })
  }
})