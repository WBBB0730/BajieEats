Page({
  data:{
    commentList:[{
      "content":"这是一条评论",
      "score":4.7,
      "commentUrls":[
      "www.baidu.com"
      ],
      "likes":59,
      "avatarUrl":"http://dummyimage.com/100x100",
      "dishInfo":{
      "dishId":1,
      "dishName":"番茄炒蛋",
      "dishImage":"http://dummyimage.com/400x400",
      "price":4,
      "colletionNum":95,
      "canteenId":96,
      "canteenName":"荔园美食汇",
      "canteenAddress":"沧海校区",
      "winId":46,
      "winName":"大众菜"
      },
      "nickName":"崔秀兰",
      "commentId":31,
      "createTime":"1981-05-24 00:54:19"
      }]
  },
  onLoad(){
    var i;
    for(i = 0;i < this.data.commentList.length;i++){
      var temp ='commentList['+i+'].createTime';
      var change = this.data.commentList[i].createTime.substring(0,10);
      this.setData({
        [temp]:change
      })
    }
  },
})