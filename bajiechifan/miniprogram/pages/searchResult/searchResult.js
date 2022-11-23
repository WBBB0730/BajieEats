Page({
  data:{
    search_value:'',
    // search_list:[{"name":"梨园"}],
    search_list:[],
    show_list_flag:true
  },
  onLoad(){
    const h_list=wx.getStorageSync('h_list');
    this.setData({
      search_value: h_list[0]
    })
    // 调接口

    // 
    if(this.data.search_list.length==0){
      this.setData({
        show_list_flag: false
      })
    }
  },
  backToSearch(){
    wx.navigateTo({
      url: '/pages/search/search',
    })
  },
  getSearchValue(e){
    console.log(e.detail.value)
    this.setData({
      search_value:e.detail.value
    })
    console.log(this.data.search_value,11)
  },
  clickSearch(){
    console.log(this.data.historyList,"origin");
    var _this = this;
    var hList = wx.getStorageSync('h_list');
        if(this.data.search_value != '') {
          hList.unshift(_this.data.search_value)
        }
          console.log(hList,"push")

    wx.setStorageSync('h_list', hList)
    var test=wx.getStorageSync('h_list')
    console.log(test,"push?")
    //刷新
    wx.reLaunch({
      url: '/pages/searchResult/searchResult',
    })
  }
})