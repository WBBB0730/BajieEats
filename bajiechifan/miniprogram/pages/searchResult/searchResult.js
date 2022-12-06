Page({
  data:{
    search_value:'',
    // search_list:[{"name":"梨园"}],
    search_list:[],
    show_list_flag:true
  },
  onLoad(o){
    console.log('start')
    let search_value = JSON.parse(o.value)
    console.log(search_value)
    this.setData({
      search_value: search_value
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

  catchFocus(){
    wx.navigateTo({
      url: '/pages/search/search',
    })
  }
})