Page({
      data: {
        star: 0,
        imgUrl: [],
        fileIDs: [],
        commentValue:''
      },
      changeColor(e) {
        var star = e.currentTarget.dataset.star;
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
        var _this = this;
        wx.chooseMedia({
          count: 3,
          sourceType: ['album', 'camera'],
          camera: 'back',
          success(res) {
            console.log(res);
            var imgList = _this.data.imgUrl;
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
        var imgList = this.data.imgUrl;
        console.log(imgList);
        var i = e.currentTarget.dataset.i;
        console.log(i)
        imgList.splice(i, 1);
        this.setData({
          imgUrl: imgList
        })
        console.log(this.data.imgUrl, 1)
      },
      submit() {
        var _this = this;
        for (var j = 0; j < this.data.imgUrl.length; j++) {
          console.log(_this.data.imgUrl[j], j);
          let temp = _this.data.imgUrl[j].split(".");
          let fileType = temp[temp.length - 1];
          wx.cloud.uploadFile({
            cloudPath: 'commentPhoto/' + Date.now() + j + Math.round(Math.random() * 2e9) + "." + fileType,
            filePath: _this.data.imgUrl[j]
          }).then(res => {
            var fIDs = _this.data.fileIDs;
            fIDs.push(res.fileID);
            _this.setData({
              fileIDs: fIDs
            })
            console.log(_this.data.fileIDs, j)
          }).catch(error => {
            console.log('fail')
          })
        }
        wx.request({
          url: 'http://114.132.234.161:8888/bajie/comment/',
          method: 'POST',
          data: {
            "score": _this.data.star,
            "content": _this.data.commentValue,
            "commentUrls": _this.data.fileIDs,
            "dishId": "1"
          },
          header: {
            'contebt-Type': 'application/json'
          },
          success(res) {
            console.log(res, 'res')
          }
        })
        wx.navigateTo({
          url: '/pages/comment/comment',
        })
      },
    })