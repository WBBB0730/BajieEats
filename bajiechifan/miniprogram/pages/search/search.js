Page({
  data:{
    search_value:'',
    historyList:wx.getStorageSync('h_list')
  },
  backToHome(){
    wx.switchTab({
      url: '/pages/home/home',
    })
  },
  clear_history () {
    this.setData({
      historyList:[]
    })
    wx.setStorage({
      key:"h_list",
      data:[],
      success () {
        wx.getStorage({
            key: "h_list",
          success(res) {
            console.log(res.data,"clear")
          },
          fail(res){
            console.log("fail")
          }
        })
      }

    })
  },
  onLoad(){
    this.setData({
      historyList:wx.getStorageSync('h_list')
    })
  },
  getSearchValue(e){
    console.log(e.detail.value)
    this.setData({
      search_value:e.detail.value
    })
    console.log(this.data.search_value,11)
  },
  clickSearch(e){
    // var hList = this.data.historyList;
    console.log(this.data.historyList,"origin");
    var _this = this;
    var hList = wx.getStorageSync('h_list');
        if(e.detail.value != '') {
          hList.unshift(_this.data.search_value)
        }
        _this.setData({
            historyList: hList,
            search_value: ''
          })
          console.log(_this.data.historyList,"push")

    wx.setStorageSync('h_list', _this.data.historyList)
    var test=wx.getStorageSync('h_list')
    console.log(test,"push?")
    // console.log(this.data.historyList);

    // 跳转页面
    wx.navigateTo({
      url: '/pages/searchResult/searchResult',
    })
  },
  tap_history:function(e){
    wx.navigateTo({
      url: '/pages/searchResult/searchResult',
    })
  }
})

    //  wx.setStorage({
    //   key:"h_list",
    //   data:_this.data.historyList,
    //   success () {
    //     wx.getStorage({
    //         key: "h_list",
    //       success(res) {
    //         console.log(res.data,"get")
    //       },
    //       fail(res){
    //         console.log("fail")
    //       }
    //     })
    //   }

    // })


      //  wx.getStorage(
  //    {
  //      key:"h_list",
  //      success(res) {
  //       var hList = res.data
  //       console.log(_this.data.search_value,"value")
  //       if(e.detail.value != '') {
  //         hList.unshift(_this.data.search_value)
  //       }
  //       _this.setData({
  //         historyList: hList,
  //         search_value: ''
  //       })
  //       console.log(_this.data.historyList,"set")
  //      }
  //    },