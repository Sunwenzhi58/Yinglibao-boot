<template>
  <div>
    <Header/>
    <div class="content clearfix">
      <div class="detail-left">
        <div class="detail-left-title">{{product.productName}}（{{product.productNo}}期）</div>
        <ul class="detail-left-number">
          <li>
            <span>历史年化收益率</span>
            <p><b>{{product.rate}}</b>%</p>
            <span>历史年化收益率</span>
          </li>
          <li>
            <span>募集金额（元）</span>
            <p><b>{{product.productMoney}}</b>元</p>
            <span v-if="product.leftProductMoney>0">募集中&nbsp;&nbsp;剩余募集金额{{product.leftProductMoney}}元</span>
            <span v-else>已满标</span>
          </li>
          <li>
            <span>投资周期</span>
            <p v-if="product.productType==0"><b>{{product.cycle}}</b>天</p>
            <p v-else><b>{{product.cycle}}</b>个月</p>
          </li>

        </ul>
        <div class="detail-left-way">
          <span>收益获取方式</span>
          <span>收益返还：<i>到期还本付息</i></span>
        </div>
        <!--投资记录-->
        <div class="datail-record">
          <h2 class="datail-record-title">投资记录</h2>
          <div class="datail-record-list">
            <table align="center" width="880" border="0" cellspacing="0" cellpadding="0">
              <colgroup>
                <col style="width: 72px" />
                <col style="width: 203px" />
                <col style="width: 251px" />
                <col style="width: 354px" />
              </colgroup>
              <thead class="datail_thead">
              <tr>
                <th>序号</th>
                <th>投资人</th>
                <th>投资金额（元）</th>
                <th>投资时间</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(bid,ind) in bidList" :key="bid.id">
                <td>{{ind+1}}</td>
                <td class="datail-record-phone">{{bid.phone}}</td>
                <td>{{bid.bidMoney}}</td>
                <td>{{bid.bidTime}}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>

      </div>
      <!--右侧-->
      <div class="detail-right">
        <div class="detail-right-title">立即投资</div>
        <div class="detail-right-mode">
          <h3 class="detail-right-mode-title">收益方式</h3>
          <p class="detail-right-mode-p"><span>到期还本付息</span></p>
          <h3 class="detail-right-mode-title">我的账户可用</h3>
          <div class="detail-right-mode-rmb">
            <p>资金（元）：******</p>
            <a href="login.html" target="_blank">请登录</a>
          </div>
          <h3 class="detail-right-mode-title">预计本息收入（元）</h3>
          <form action="" id="number_submit">
            <p>请在下方输入投资金额</p>
            <input type="text" placeholder="请输入日投资金额，应为100元整倍数" name="" class="number-money" >
            <input type="submit" value="立即投资" class="submit-btn">
          </form>

        </div>

      </div>


    </div>
    <Footer/>
  </div>
</template>

<script>
import Header from "@/components/common/Header";
import Footer from "@/components/common/Footer";
import {doGet} from "@/api/httpRequest";

export default {
  name: "ProductDetail",
  components:{
    // eslint-disable-next-line vue/no-unused-components
    Header,
    // eslint-disable-next-line vue/no-unused-components
    Footer
  },
  data(){
    return{
    product:{
          id: 0,
          productName: "",
          rate: 0,
          cycle: 0,
          releaseTime: 0,
          productType: 0,
          productNo: "",
          productMoney: 0,
          leftProductMoney: 0,
          bidMinLimit: 0,
          bidMaxLimit: 0,
          productStatus: 0,
          productFullTime: 0,
          productDesc: "",
          version: 0
        },
      bidList:[{
       id:3214,
        phone:"",
        bidTime:"",
        bidMoney:0
      }]
    }
  },
  mounted() {
    let productId=this.$route.query.productId;
    doGet('/v1/product/info',{productId:productId})
    .then(resp=>{
      if (resp){
        this.product=resp.data.data;
        this.bidList=resp.data.list;
      }
    })
  }
}
</script>

<style scoped>

</style>