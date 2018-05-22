var vm = new Vue({
    el : '#rrapp',
    data : {
        title : "第三方配置",
        sina : {
            client_ID : null
        },
        baidu : {
            client_ID : null
        },
        weixin : {
            appId : null
        },
        qq : {
            client_ID : null
        },
        github : {
            client_ID : null
        },
        thirdpart : 2,
        
        ali : {
            client_ID : null
        }
    },
    methods : {
        query : function() {
            vm.title = "第三方设置";
            $.ajax({
                type : "POST",
                url : rootPath + "/thirdoartlogin_do/info.do",
                dataType : "json",
                success : function(data) {
                    vm.qq = data.qq;
                    vm.sina = data.sina;
                    vm.baidu = data.baidu;
                    vm.weixin = data.weixin;
                    vm.github = data.github;
                    vm.ali = data.ali;
                }
            });
        },
        update : function() {
            $.ajax({
                type : "POST",
                url : rootPath + "/thirdoartlogin_do/update.do",
                data : {
                    baiduid : vm.baidu.client_ID,
                    baiduSERCRET : vm.baidu.client_SERCRET,
                    sinaid : vm.sina.client_ID,
                    sinaSERCRET : vm.sina.client_SERCRET,
                    weixinid : vm.weixin.appId,
                    weixinSERCRET : vm.weixin.secret,
                    qqid : vm.qq.client_ID,
                    qqSERCRET : vm.qq.client_SERCRET,
                    githubid : vm.github.client_ID,
                    githubSERCRET : vm.github.client_SERCRET,
                    aliid : vm.ali.client_ID,
                    aliSERCRET : vm.ali.client_SERCRET,
                    alipublickey:vm.ali.alipublickey
                },
                success : function(data) {
                    alert(data.msg);
                }
            });
        }
    }
});
vm.query();
