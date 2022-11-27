Page({
      data: {
        star: 0,
        imgUrl: [],
        fileIDs: [],
        commentValue:'',
        dishImage:'',
        dishName:'',
        canteenName:'',
        canteenAddress:'',
        winName:'',
        dishId: 0 
      },
      onShow(){
      let  _this = this;
        wx.request({
          url: 'http://114.132.234.161:8888/bajie/dish/simple',
          method: 'GET',
          data:{
            dishId:1
          },
          header:{
            'content-type':'application/x-www-form-urlencoded',
          },
          success(res){
            var resstr=JSON.stringify(res.data);
            var resobj=JSON.parse(resstr);
            _this.setData({
              dishId:resobj.data.dishId,
              dishImage:resobj.data.dishImage,
              dishName:resobj.data.dishName,
              canteenName:resobj.data.canteenName,
              canteenAddress:resobj.data.canteenAddress,
              winName:resobj.data.winName
            })
          },
          fail(){
            console.log(0);
          }
        })
      },
      changeColor(e) {
        let star = e.currentTarget.dataset.star;
        console.log(star, 'e')
        this.setData({
          star: star
        })
        console.log(this.data.star, 'data')
      },
      getCommentValue(e){
        console.log(e.detail.value);
        this.setData({
          commentValue: e.detail.value
        })
      },
      chooseImg() {
        let _this = this;
        wx.chooseMedia({
          count: 3,
          sourceType: ['album', 'camera'],
          camera: 'back',
          success(res) {
            console.log(res);
            let imgList = _this.data.imgUrl;
            console.log(imgList)
            imgList.push(res.tempFiles[0].tempFilePath);
            _this.setData({
              imgUrl: imgList
            })
            console.log(_this.data.imgUrl)
          },
          fail() {

          }
        })
      },
      deletePhoto(e) {
        let imgList = this.data.imgUrl;
        console.log(imgList);
        let i = e.currentTarget.dataset.i;
        console.log(i)
        imgList.splice(i, 1);
        this.setData({
          imgUrl: imgList
        })
        console.log(this.data.imgUrl, 1)
      },
      //提交时触发
      submit() {
        let _this = this;
        let cnt = 0;
        let app = getApp();
        let fIDs = new Array(this.data.imgUrl.length);
        console.log(app.globalData.token,"submit")
        if(this.data.imgUrl.length!=0){
          for (let j = 0; j < this.data.imgUrl.length; j++) {
            console.log(_this.data.imgUrl[j], j);
            let temp = _this.data.imgUrl[j].split(".");
            let fileType = temp[temp.length - 1];
            wx.cloud.uploadFile({
              cloudPath: 'commentPhoto/' + Date.now() + j + Math.round(Math.random() * 2e9) + "." + fileType,
              filePath: _this.data.imgUrl[j]
            }).then(res => {
              fIDs[j] = res.fileID;
              console.log(fIDs,'fids');
              console.log(j,cnt);
              if(cnt++==_this.data.imgUrl.length-1){
                _this.setData({
                  fileIDs: fIDs
                })
                console.log(_this.data.fileIDs, 'fileid')
                wx.request({
                  url: 'http://114.132.234.161:8888/bajie/comment/',
                  method: 'POST',
                  data: {
                    "score": _this.data.star,
                    "content": _this.data.commentValue,
                    "commentUrls":_this.data.fileIDs,
                    "dishId": "1"
                  },
                  header: {
                    'token' : app.globalData.token
                  },
                  success(res) {
                    console.log(res, 'res')
                    _this.setData({
                      imgUrl:[],
                      fileIDs:[],
                      star: 0,
                      commentValue:''
                    })
                    wx.navigateTo({
                      url: '/pages/comment/comment',
                    })
                  }
                })
              }
            }).catch(error => {
              console.log(error)
            })
          }
        }
        else{
          wx.request({
            url: 'http://114.132.234.161:8888/bajie/comment/',
            method: 'POST',
            data: {
              "score": _this.data.star,
              "content": _this.data.commentValue,
              "commentUrls":_this.data.fileIDs,
              "dishId": "1"
            },
            header: {
              'token' : app.globalData.token
            },
            success(res) {
              console.log(res, 'res')
              wx.navigateTo({
                url: '/pages/comment/comment',
              })
            }
          })
        }
      },
      toDish(){
        let _this = this;
        wx.navigateTo({
          url: '/pages/dish/dish?id=' + _this.data.dishId,
        })
      }
    })