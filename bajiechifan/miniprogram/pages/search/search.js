Page({
      data: {
        search_value: '',
        historyList: [],
        focus: true
      },
      backToHome() {
        wx.switchTab({
          url: '/pages/home/home',
        })
      },
      clear_history() {
        this.setData({
          historyList: []
        })
        wx.setStorage({
          key: "h_list",
          data: [],
          success() {
            wx.getStorage({
              key: "h_list",
              success(res) {
                console.log(res.data, "clear history");
              },
              fail(res) {
                console.log("fail clear history");
              }
            })
          }

        })
      },
      onLoad() {
        this.setData({
          historyList: wx.getStorageSync('h_list')
        })
        if (!this.data.historyList) {
          this.setData({
            historyList: []
          })
        }
      },

      onShow() {
        let _this = this;
        _this.setData({
          focus: true
        });
      },

      getSearchValue(e) {
        this.setData({
          search_value: e.detail.value
        })
      },
      clearInput() {
        let _this = this;
        _this.setData({
          search_value: ""
        })
      },
      clickSearch() {
        // var hList = this.data.historyList;
        var hList = this.data.historyList;
        console.log(hList, 'hlist',this.data.search_value)
        if (this.data.search_value) {
          for (let i = 0; i < hList.length; i++) {
            if (hList[i] == this.data.search_value) {
              console.log(hList[i], this.data.search_value, '???')
              hList.splice(i, 1)
            }
          }
          hList.unshift(this.data.search_value)
        }
          this.setData({
            historyList: hList,
          })
          console.log(this.data.historyList, "push")

          wx.setStorageSync('h_list', this.data.historyList)
          var test = wx.getStorageSync('h_list')
          console.log(test, "push?")
          // console.log(this.data.historyList);

          // 跳转页面
          let value = JSON.stringify(this.data.search_value);
          wx.navigateTo({
            url: '/pages/searchResult/searchResult?value=' + value,
          })
        
      },
        tap_history(e) {
          let value = e.currentTarget.dataset.value;
          let index = e.currentTarget.dataset.index;
          let hList = this.data.historyList;
          hList.splice(index,1);
          hList.unshift(value);
          this.setData({
            search_value: value,
            historyList: hList
          })
          wx.setStorageSync('h_list', hList)
          value = JSON.stringify(value);
          wx.navigateTo({
            url: '/pages/searchResult/searchResult?value=' + value,
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