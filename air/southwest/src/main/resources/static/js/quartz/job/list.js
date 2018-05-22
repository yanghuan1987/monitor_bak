var vm = new Vue({
    el : '#rrapp',
    data : {
        q : {
            beanName : null
        },
        showList : true,
        title : null,
        schedule : {}
    },
    methods : {
        query : function() {
            vm.reload();
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增";
            vm.schedule = {};
        },
        update : function() {
            var jobId = getSelectedRow('jobtable', 'job_Id');
            if (jobId == null) {
                return;
            }
            $.get(rootPath + "/system/schedule/info.do?jobId=" + jobId, function(r) {
                vm.showList = false;
                vm.title = "修改";
                vm.schedule = r.schedule;
            });
        },
        saveOrUpdate : function(event) {
            var url = vm.schedule.jobId == null ? rootPath + '/system/schedule/create.do' : rootPath + '/system/schedule/update.do';
            $.ajax({
                type : "POST",
                url : rootPath + url,
                contentType : "application/json",
                data : JSON.stringify(vm.schedule),
                success : function(r) {
                    vm.reload();
                    alert(r.msg);
                }
            });
        },
        del : function() {
            var jobIds = getSelectedRows('jobtable', 'job_Id');
            if (jobIds == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/system/schedule/delete.do",
                    // contentType : "application/json",
                    data : {
                        jobIds : jobIds
                    },
                    success : function(r) {
                        vm.reload();
                        alert(r.msg);
                    }
                });
            });
        },
        pause : function() {
            var jobIds = getSelectedRows('jobtable', 'job_Id');
            if (jobIds == null) {
                return;
            }
            confirm('确定要暂停选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/system/schedule/pause.do",
                    // contentType : "application/json",
                    data : {
                        jobIds : jobIds
                    },
                    success : function(r) {
                        vm.reload();
                        alert(r.msg);
                    }
                });
            });
        },
        resume : function() {
            var jobIds = getSelectedRows('jobtable', 'job_Id');
            if (jobIds == null) {
                return;
            }
            confirm('确定要恢复选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/system/schedule/resume.do",
                    // contentType : "application/json",
                    data : {
                        jobIds : jobIds
                    },
                    success : function(r) {
                        vm.reload();
                        alert(r.msg);
                    }
                });
            });
        },
        runOnce : function() {
            var jobIds = getSelectedRows('jobtable', 'job_Id');
            if (jobIds == null) {
                return;
            }
            confirm('确定要立即执行选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/system/schedule/run.do",
                    // contentType : "application/json",
                    data : {
                        jobIds : jobIds
                    },
                    success : function(r) {
                        vm.reload();
                        alert(r.msg);
                    }
                });
            });
        },
        reload : function(event) {
            vm.showList = true;
            layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem : '#jobtable' // 选定是那个DIV
                    ,
                    url : rootPath + '/system/schedule/list.do',
                    cols : [
                        [
                            {
                                type : 'checkbox'
                            }, {
                                field : 'jobId',
                                width : 180,
                                title : '任务ID'
                            }, {
                                field : 'beanName',
                                width : 100,
                                title : 'Spring Bean'
                            }, {
                                field : 'methodName',
                                title : '方法名',
                                width : 100
                            }, {
                                field : 'params',
                                title : '参数',
                                width : 100
                            }, {
                                field : 'status',
                                title : '状态',
                                width : 100,
                                templet : function(data) {
                                    if (data.status == 0) {
                                        return "开启";
                                    }
                                    if (data.status == 1) {
                                        return "暂停";
                                    }
                                    return "数据异常";
                                }
                            }, {
                                field : 'remark',
                                title : '备注',
                                width : 100
                            }, {
                                field : 'cronExpression',
                                title : '定时表达式',
                                width : 100
                            }, {
                                field : 'createTime',
                                title : '创建时间',
                                width : 100
                            } ] ],
                    page : true, // 开启分页
                    request : laypagerequest,
                    response : laypageresponse,
                // where : $("#searchForm").serializeJSON()
                });
            });
        }
    }
});
vm.reload();
