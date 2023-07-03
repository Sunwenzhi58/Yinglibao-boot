package com.bjpowernode.front.controller;

import com.bjpowernode.api.model.ProductInfo;
import com.bjpowernode.api.pojo.MultiProduct;
import com.bjpowernode.common.enums.RCode;
import com.bjpowernode.common.util.CommonUtil;
import com.bjpowernode.front.view.PageInfo;
import com.bjpowernode.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Package:com.bjpowernode.front.controller
 * Date:2022/3/3 15:18
 */
@Api(tags = "理财产品功能")
@RestController
@RequestMapping("/v1")
public class ProductController extends BaseController {

    @ApiOperation(value = "首页三类产品列表",notes = "一个新手宝，三个优选，三个散标产品")
    @GetMapping("/product/index")
    public RespResult queryProductIndex(){
        RespResult result = RespResult.ok();
        MultiProduct multiProduct = productService.queryIndexPageProducts();
        result.setData(multiProduct);
        return result;

    }


    /*按产品类型分页查询*/
    @GetMapping("/product/list")
    public RespResult queryProductByType(@RequestParam("ptype") Integer pType,
                                         @RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                                         @RequestParam(value = "pageSize",required = false,defaultValue = "9") Integer pageSize){
        RespResult result = RespResult.fail();
        if(pType != null && (pType == 0 || pType == 1 || pType == 2)){
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            //分页处理，记录总数
            Integer recordNums = productService.queryRecordNumsByType(pType);
            if( recordNums > 0 ){
                //产品集合
                List<ProductInfo> productInfos = productService.queryByTypeLimit(pType,pageNo,pageSize);
                //构建PageInfo
                PageInfo page = new PageInfo(pageNo,pageSize,recordNums);

                result = RespResult.ok();
                result.setList(productInfos);
                result.setPage(page);
            }
        } else {
            //请求参数有误
            result.setRCode(RCode.REQUEST_PRODUCT_TYPE_ERR);
        }
        return result;

    }

}