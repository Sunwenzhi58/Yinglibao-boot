import axios from "axios";
import qs from 'qs'
//设置默认值
axios.defaults.baseURL="http://localhost:8000/api";
axios.defaults.timeout=50000


//封装get请求方法,url请求的地址，去掉了baseurl以外的部分
//params:是对象，json对象，表示请求的参数
function doGet(url,params){
     return  axios({
         url:url,
         method:'get',
         params:params
     });
}

function doPostJson(url,params){
    return axios({
        url:url,
        method:'post',
        data:params
    })
}

function doPost(url,params){
    //qs 把json对象转为a=1&b=2格式，也可以反向
    let requestData = qs.stringify(params)
    return axios.post(url,requestData)
}
//导出，暴露这个函数，其他模块才能使用
export {doGet,doPostJson,doPost}