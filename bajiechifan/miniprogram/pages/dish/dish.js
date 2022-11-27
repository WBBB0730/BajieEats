Page({
  data: {
    dishId:0,
    dishName:'',
    dishImage:'',
    dishType:'大众菜',
    price:0,
    tags:'',
    ingredient:'',
    score:0,
    labelList:[{"name": "咸鲜"},{"name": "大众菜"}],
    commentList:[],
    commentUrls:[],
    commentListNum:0,
    collectionFlag: false,
    dialogFlag: false,
    // cardType为0则为详情，为1则为评价
    cardType: 0,
    commentType:null,
  },
  onShow(){
    var _this = this;
    //菜品详情信息
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/dish/',
      method: 'GET',
      data:{
        dishId:1
      },
      header: {
        'contebt-Type':'application/json'
      },
      success(res){
        var resstr=JSON.stringify(res.data)
        var resobj=JSON.parse(resstr)
        _this.setData({
          dishId:resobj.data.dishId,
          dishName:resobj.data.dishName,
          dishImage:resobj.data.dishImage,
          price:resobj.data.price,
          score:resobj.data.score,
          ingredient:resobj.data.ingredients,
          tags:resobj.data.tags
        })
        console.log(resobj)
      },
      fail(){
        console.log("fail")
      }
    })
  //   评价接口
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/comment/list',
      method: 'GET',
      data:{
        dishId:1,
        pageIndex:1,
        pageSize:10
      },
      header: {
        'contebt-Type':'application/json'
      },
      success(res){
        var resstr=JSON.stringify(res.data)
        var resobj=JSON.parse(resstr)
        _this.setData({
          commentList:resobj.data.list,
          commentListNum:resobj.data.list.length
        })
        console.log(resobj,'comment')
      },
      fail(){
        console.log("fail")
      }
    })
  },
  clickCollectionIcon(e){
    this.setData(
      {collectionFlag:!this.data.collectionFlag}
    )
  },
  toDetail(e){
    this.setData(
      {cardType:0}
    )
  },
  toComment(e){
    this.setData(
      {cardType:1}
    )
  },
  // 评价按钮
  toGood(e){
    this.setData(
      {commentType: 'Good'}
    )
  },
  toMiddle(e){
    this.setData(
      {commentType: 'Middle'}
    )
  },
  toBad(e) {
    this.setData(
      {commentType: 'Bad'}
    )
  },
  selectDialog(e) {
    if(this.data.dialogFlag) {
      this.setData(
        {dialogFlag: false}
      )
    }
    else {
      this.setData(
        {dialogFlag: true}
      )
    }
  },
  cancelDialog(e) {
    this.setData(
      {dialogFlag: false}
    )
  },
  toPost(){
    let app = getApp();
    console.log(app.globalData.token,1);
    if(app.globalData.token){
      wx.navigateTo({
        url: '/pages/postComment/postComment',
      })
    }
    else{
      wx.showModal({
        title: '提示',
        content: '请先授权登录~',
        success(res){
          if(res.confirm){
            wx.switchTab({
              url: '/pages/mine/mine',
            })
          }
          else{
            console.log('stay')
          }
        }
      })
    }
  }
});
