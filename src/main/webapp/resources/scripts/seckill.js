//存放主要的交互罗技的js代码
var seckill = {
    //封装秒杀相关的ajax url
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },

    handlerSeckillkill: function (seckillId, node) {
        //处理秒杀逻辑
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            //在回调函数中执行交互流程
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //开启秒杀
                    var md5 = exposer['md5'];
                    var killURL = seckill.URL.execution(seckillId, md5);
                    console.log("killURL:" + killURL);
                        $('#killBtn').one('click', function () {
                            //1.禁用按钮
                            $(this).addClass('disabled');
                            //发送请求执行秒杀
                            $.post(killURL, {}, function (result) {
                                if (result && result['success']) {
                                    var killResult = result['data'];
                                    var state = killResult['state'];
                                    var stateInfo = killResult['stateInfo'];
                                    //显示结果
                                    node.html('<span class="label label-success">' + stateInfo + '</span>')
                                }
                            });
                    });
                    node.show();
                } else {
                    //未开启
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    //重新计时
                    seckill.countdown(seckillId, now, start, end);
                }
            } else {
                console.log('result' + result);
            }
        })
    },
    //验证手机号码
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    countdown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        //时间判断
        if (nowTime > endTime) {
            seckillBox.html('秒杀结束!');
        } else if (nowTime < startTime) {//秒杀未开始
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                //时间完成后回调事件
            }).on('finish.countdown', function () {
                //获取秒杀地址,执行秒杀
                seckill.handlerSeckillkill(seckillId, seckillBox);
            });
        } else {
            //秒杀开始
            seckill.handlerSeckillkill(seckillId, seckillBox);
        }
    },
    //详情页秒杀的逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证和登录
            //在cookie中查找phone
            var killPhone = $.cookie('killPhone');

            //验证手机
            if (!seckill.validatePhone(killPhone)) {
                //绑定phone
                //控制输出
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true,//显示弹出层
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false//关闭键盘事件
                });

                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        //电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'})
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机格式错误</label>').show(300);
                    }
                });
            }
            //已经登录
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log("result" + result);
                }
            });
        }
    }
}