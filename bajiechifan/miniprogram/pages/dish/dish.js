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
    commentList:[ {
      "commentId": 1,
      "nickName": "微信用户1",
      "avatarUrl": "https://img1.baidu.com/it/u=592570905,1313515675&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
      "content": "非常好吃耶！！",
      "likes": 10,
      "score": 4.7,
      "openId": "XXX",
      "dishId": 1,
      "createTime": "2022-11-12",
      "updateTime": "2022-11-12"
  },
  {
      "commentId": 3,
      "nickName": "微信用户3",
      "avatarUrl": "https://img0.baidu.com/it/u=2267130927,4233025274&fm=253&fmt=auto&app=120&f=JPEG?w=800&h=800",
      "content": "还行吧",
      "likes": 12,
      "score": 4.7,
      "openId": "XXX",
      "dishId": 1,
      "createTime": "2022-11-12",
      "updateTime": "2022-11-12"
  }],
    // commentListNum:0,
    collectionFlag: false,
    dialogFlag: false,
    // cardType为0则为详情，为1则为评价
    cardType: 0,
    commentType:null,
  },
  onLoad(){
    var _this = this;
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
  //   wx.request({
  //     url: 'http://114.132.234.161:8888/bajie/comment/list',
  //     method: 'GET',
  //     data:{
  //       dishId:1,
  //       pageIndex:1,
  //       pageSize:10
  //     },
  //     header: {
  //       'contebt-Type':'application/json'
  //     },
  //     success(res){
  //       var resstr=JSON.stringify(res.data)
  //       var resobj=JSON.parse(resstr)
  //       _this.setData({
  //         commentList:resobj.data.list,
  //         commentListNum:commentList.length
  //       })
  //       console.log(resobj)
  //     },
  //     fail(){
  //       console.log("fail")
  //     }
  //   })
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
  }
});
