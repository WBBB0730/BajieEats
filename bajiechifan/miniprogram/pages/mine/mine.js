Page({
  data: {
    avatarUrl:"",
    name:"",
    flag:"0",
    myset:[
      {
        'name':'我发布的评论',
      },
      {
        'name':'我发布的失物招领',
      },
      {
        'name':'我发布的寻物启事',
      },
      {
        'name':'我的收藏',
      }
    ],
    set:"设置",
  },

  getUserProfile:function(){
    wx.login({
      success: (res) => {
        if(res.code){
          wx.request({
            url:'api',
            data:{
              code:res.code,
            },
            success:function(result){
              const res=JSON.parse(result);
              const {openid,session_key,unionid,errorcode}=res.data;
              this.data.openid=openid;
              this.data['session_key']=session_key;
              this.data.unionid=unionid;
              this.setStorage({
                key:wxloginInfo,
                value:{
                    openid:openid,
                    session_key:session_key,
                    unionid:unionid
                },
              });
            },
          })
        }
        else{
          console.log('登录失败'+res.errMsg);
        }
      },
    })
      wx.getUserProfile({
        desc: '你就说给不给吧',
        success:(res)=>{
          console.log('success',res)
          wx.setStorage({data:res.userInfo,key:'userInfo',});
          this.setData({flag:1})
          this.onShow();
        }
      })
  },

  Toinformation:function(e){
    wx.navigateTo({
      url: '../information/information',
    })
  },

  tomyset:function(e){
    if(e.currentTarget.dataset.type=='我发布的评论'){
      wx.navigateTo({
        url: '../comment/comment',
      })
    }
    else if(e.currentTarget.dataset.type=='我发布的失物招领'){
      wx.navigateTo({
        url: '../Lost_and_Found/Lost_and_Found',
      })
    }
    else if(e.currentTarget.dataset.type=='我发布的寻物启事'){
      wx.navigateTo({
        url: '../Notice_for_Lost/Notice_for_Lost',
      })
    }
    else if(e.currentTarget.dataset.type=='我的关注'){
      wx.navigateTo({
        url: '../Follow/Follow',
      })
    }
  },

  onShow:function(){
    var useInfo=wx.getStorageSync('userInfo')
    var that=this;
    that.setData({
      avatarUrl:useInfo.avatarUrl,
      name:useInfo.nickName,
    })
  }
})