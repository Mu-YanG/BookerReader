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
 * ���룺���� JSON �ַ�����������ͬ�ṹ���б���Ϣ��������ʾ��
�����һ�� JSON �ַ����������������� JSON �ַ����ıȽϽ����������ʾ��
˵�����ȶԽ��������������������item_modify����ɾ����item_remove����������item_add����ʹ���б����еĵ�һ��Ԫ�أ���1�е� name, ��2�е� ENTNAME����Ϊ ID ʶ��仯ǰ����б����Ƿ��Ӧ��ͬ��ʵ�塣���ڱ��������������ֻ���� ID �ͷ��������Ԫ�أ�ʡ��û�з��������Ԫ�ء�



ʾ������
�����ַ���һ��
[
    {
        "ESDATE": "1999-06-03",
        "ENTSTATUS": "��Ӫ����ҵ��",
        "BINVVAMOUNT": "193",
        "REGCAP": "6600.000000",
        "SUBCONAM": "19.095000",
        "REGCAPCUR": "�����",
        "FUNDEDRATIO": "0.29%",
        "ENTNAME": "������ҵ",
        "ENTTYPE": "�������ι�˾",
        "CONFORM": "����"
    },
    {
        "ESDATE": "1981-11-02",
        "ENTSTATUS": "��Ӫ����ҵ��",
        "BINVVAMOUNT": "193",
        "REGCAP": "1952156.490000",
        "SUBCONAM": "1952156.490000",
        "REGCAPCUR": "�����",
        "FUNDEDRATIO": "100.00%",
        "ENTNAME": "������",
        "ENTTYPE": "�����������ι�˾",
        "CONFORM": ""
    },
    {
        "ESDATE": "2015-12-15",
        "ENTSTATUS": "��Ӫ����ҵ��",
        "BINVVAMOUNT": "193",
        "REGCAP": "100000.000000",
        "SUBCONAM": "5000.000000",
        "REGCAPCUR": "�����",
        "FUNDEDRATIO": "5.00%",
        "ENTNAME": "��������",
        "ENTTYPE": "�����������ι�˾",
        "CONFORM": "����"
    }
]

�����ַ���2��
[
    {
        "ESDATE": "1981-11-02",
        "ENTSTATUS": "��Ӫ����ҵ��",
        "BINVVAMOUNT": "234",
        "REGCAP": "1952156.490000",
        "SUBCONAM": "1952156.490000",
        "REGCAPCUR": "�����",
        "FUNDEDRATIO": "100.00%",
        "ENTNAME": "������",
        "ENTTYPE": "�����������ι�˾",
        "CONFORM": ""
    },
    {
        "ESDATE": "2015-12-15",
        "ENTSTATUS": "��Ӫ����ҵ��",
        "BINVVAMOUNT": "234",
        "REGCAP": "200000.000000",
        "SUBCONAM": "5000.000000",
        "REGCAPCUR": "�����",
        "FUNDEDRATIO": "2.50%",
        "ENTNAME": "��������",
        "ENTTYPE": "�����������ι�˾",
        "CONFORM": "����"
    },
    {
        "ESDATE": "2009-03-17",
        "ENTSTATUS": "��Ӫ����ҵ��",
        "BINVVAMOUNT": "234",
        "REGCAP": "40600.000000",
        "SUBCONAM": "8420.440000",
        "REGCAPCUR": "�����",
        "FUNDEDRATIO": "20.74%",
        "ENTNAME": "ɽ����װ",
        "ENTTYPE": "�����������ι�˾",
        "CONFORM": ""
    }
]


����ַ�����
{
    "item_add": [{"content_after": {
        "ESDATE": "2009-03-17",
        "ENTSTATUS": "��Ӫ����ҵ��",
        "BINVVAMOUNT": "234",
        "REGCAP": "40600.000000",
        "SUBCONAM": "8420.440000",
        "REGCAPCUR": "�����",
        "FUNDEDRATIO": "20.74%",
        "ENTNAME": "ɽ����װ",
        "ENTTYPE": "�����������ι�˾",
        "CONFORM": ""
    }}],
    "item_modify": [
        {
            "content_after": {
                "BINVVAMOUNT": "234",
                "ENTNAME": "������"
            },
            "content_before": {
                "BINVVAMOUNT": "193",
                "ENTNAME": "������"
            }
        },
        {
            "content_after": {
                "BINVVAMOUNT": "234",
                "REGCAP": "200000.000000",
                "FUNDEDRATIO": "2.50%",
                "ENTNAME": "��������"
            },
            "content_before": {
                "BINVVAMOUNT": "193",
                "REGCAP": "100000.000000",
                "FUNDEDRATIO": "5.00%",
                "ENTNAME": "��������"
            }
        }
    ],
    "item_remove": [{"content_before": {
        "ESDATE": "1999-06-03",
        "ENTSTATUS": "��Ӫ����ҵ��",
        "BINVVAMOUNT": "193",
        "REGCAP": "6600.000000",
        "SUBCONAM": "19.095000",
        "REGCAPCUR": "�����",
        "FUNDEDRATIO": "0.29%",
        "ENTNAME": "������ҵ",
        "ENTTYPE": "�������ι�˾",
        "CONFORM": "����"
    }}]
}
 * 
 * */
public class DataMergingController {

	
	


   
   
   
   
}
