Page({
  data:{
    search_value:'',
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
    this.getData()
    // 
  },
  //获取数据调接口
  getData(){
    let that = this;
     wx.getStorage(
      {
        key:'location',
        success(res){
          console.log(res);
          let longitude = res.data[0];
          let altitude = res.data[1];
          wx.request({
            method: 'POST',
            url: 'http://114.132.234.161:8888/bajie/search/all',
            header: {
              'content-Type': 'application/x-www-form-urlencoded'
            },
            data:{
              keyWord: that.data.search_value,
              longitude: longitude,
              latitude: altitude
            },
            success(res){
              console.log(res.data.data,'serach'),
              that.setData({
                search_list: res.data.data
              })
              if(that.data.search_list.length==0){
                that.setData({
                  show_list_flag: false
                })
              }
            }
          })
        },
        fail(){
          console.log('getstorage fail')
        }
      }
    )
  },
  //返回按钮
  backToSearch(){
    wx.navigateTo({
      url: '/pages/search/search',
    })
  },
 //点击搜索回到搜索页面
  catchFocus(){
    wx.navigateTo({
      url: '/pages/search/search',
    })
  },
  toCanteen(e) {
    console.log(e.currentTarget.dataset.id)
    let id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '/pages/canteenDetails/canteenDetails?id=' + id,
    })
  }
})