package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;



/**
 * 
 * 输入：两个 JSON 字符串，包含相同结构的列表信息，见下面示例
输出：一个 JSON 字符串，包含两个输入 JSON 字符串的比较结果，见下面示例
说明：比对结果包含三种情况：变更（item_modify）、删除（item_remove）和新增（item_add），使用列表项中的第一个元素（例1中的 name, 例2中的 ENTNAME）作为 ID 识别变化前后的列表项是否对应相同的实体。对于变更的情况，输出中只包含 ID 和发生变更的元素，省略没有发生变更的元素。



示例二：
输入字符串一：
[
    {
        "ESDATE": "1999-06-03",
        "ENTSTATUS": "在营（开业）",
        "BINVVAMOUNT": "193",
        "REGCAP": "6600.000000",
        "SUBCONAM": "19.095000",
        "REGCAPCUR": "人民币",
        "FUNDEDRATIO": "0.29%",
        "ENTNAME": "湖天种业",
        "ENTTYPE": "有限责任公司",
        "CONFORM": "货币"
    },
    {
        "ESDATE": "1981-11-02",
        "ENTSTATUS": "在营（开业）",
        "BINVVAMOUNT": "193",
        "REGCAP": "1952156.490000",
        "SUBCONAM": "1952156.490000",
        "REGCAPCUR": "人民币",
        "FUNDEDRATIO": "100.00%",
        "ENTNAME": "淮矿集团",
        "ENTTYPE": "其他有限责任公司",
        "CONFORM": ""
    },
    {
        "ESDATE": "2015-12-15",
        "ENTSTATUS": "在营（开业）",
        "BINVVAMOUNT": "193",
        "REGCAP": "100000.000000",
        "SUBCONAM": "5000.000000",
        "REGCAPCUR": "人民币",
        "FUNDEDRATIO": "5.00%",
        "ENTNAME": "西王集团",
        "ENTTYPE": "其他有限责任公司",
        "CONFORM": "其他"
    }
]

输入字符串2：
[
    {
        "ESDATE": "1981-11-02",
        "ENTSTATUS": "在营（开业）",
        "BINVVAMOUNT": "234",
        "REGCAP": "1952156.490000",
        "SUBCONAM": "1952156.490000",
        "REGCAPCUR": "人民币",
        "FUNDEDRATIO": "100.00%",
        "ENTNAME": "淮矿集团",
        "ENTTYPE": "其他有限责任公司",
        "CONFORM": ""
    },
    {
        "ESDATE": "2015-12-15",
        "ENTSTATUS": "在营（开业）",
        "BINVVAMOUNT": "234",
        "REGCAP": "200000.000000",
        "SUBCONAM": "5000.000000",
        "REGCAPCUR": "人民币",
        "FUNDEDRATIO": "2.50%",
        "ENTNAME": "西王集团",
        "ENTTYPE": "其他有限责任公司",
        "CONFORM": "其他"
    },
    {
        "ESDATE": "2009-03-17",
        "ENTSTATUS": "在营（开业）",
        "BINVVAMOUNT": "234",
        "REGCAP": "40600.000000",
        "SUBCONAM": "8420.440000",
        "REGCAPCUR": "人民币",
        "FUNDEDRATIO": "20.74%",
        "ENTNAME": "山能重装",
        "ENTTYPE": "其他有限责任公司",
        "CONFORM": ""
    }
]


输出字符串：
{
    "item_add": [{"content_after": {
        "ESDATE": "2009-03-17",
        "ENTSTATUS": "在营（开业）",
        "BINVVAMOUNT": "234",
        "REGCAP": "40600.000000",
        "SUBCONAM": "8420.440000",
        "REGCAPCUR": "人民币",
        "FUNDEDRATIO": "20.74%",
        "ENTNAME": "山能重装",
        "ENTTYPE": "其他有限责任公司",
        "CONFORM": ""
    }}],
    "item_modify": [
        {
            "content_after": {
                "BINVVAMOUNT": "234",
                "ENTNAME": "淮矿集团"
            },
            "content_before": {
                "BINVVAMOUNT": "193",
                "ENTNAME": "淮矿集团"
            }
        },
        {
            "content_after": {
                "BINVVAMOUNT": "234",
                "REGCAP": "200000.000000",
                "FUNDEDRATIO": "2.50%",
                "ENTNAME": "西王集团"
            },
            "content_before": {
                "BINVVAMOUNT": "193",
                "REGCAP": "100000.000000",
                "FUNDEDRATIO": "5.00%",
                "ENTNAME": "西王集团"
            }
        }
    ],
    "item_remove": [{"content_before": {
        "ESDATE": "1999-06-03",
        "ENTSTATUS": "在营（开业）",
        "BINVVAMOUNT": "193",
        "REGCAP": "6600.000000",
        "SUBCONAM": "19.095000",
        "REGCAPCUR": "人民币",
        "FUNDEDRATIO": "0.29%",
        "ENTNAME": "湖天种业",
        "ENTTYPE": "有限责任公司",
        "CONFORM": "货币"
    }}]
}
 * 
 * */
public class DataMergingController {

	
	


   
   
   
   
}
