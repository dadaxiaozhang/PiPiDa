package com.lanou3g.action;

import com.lanou3g.daomain.Area;
import com.lanou3g.daomain.Server;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public class CitySelectAction extends ActionSupport {

    //添加了登录注册的功能
    private List<Area> areaList;
    //定义变量用来接收选择的是哪个大区
    private int index;
    private List<Server> servers;

    public CitySelectAction() {
        //添加假数据，正常情况下数据应该从数据库中传出来
        areaList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Area area = new Area();
            area.setId(i);
            area.setAname("网通" + (i+1) + "蛆");

            //在大蛆中创建10个服务器
            List<Server> servers=new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Server s=new Server();
                s.setId(j);
                s.setSname("小霸王"+(j+1)+"号");
                //存储服务器
                servers.add(s);
            }
            //把服务器放到大蛆对象中
            area.setServers(servers);
            areaList.add(area);
        }
    }

    public String playGame() {
        return SUCCESS;
    }

    public String getServer() throws IOException {
        //返回json数据
        //获取选择的大区中服务器数据
        List<Server> servers= areaList.get(index).getServers();
        //把集合数据转换为JSON格式，写入响应体内
        String json= JSONArray.fromObject(servers).toString();
        HttpServletResponse resp= ServletActionContext.getResponse();
        //设置响应头
        resp.setContentType("application/json;charset=utf-8");
        //写入数据
        resp.getWriter().print(json);

        return null;
    }

    //使用struts2的方式向客户端返回json数据
    public String getServerJson(){
        //给需要转化的数据类对象赋值
        //获取某各大区中的所有服务器集合
        //注意：数据类必须为成员变量
        servers = areaList.get(index).getServers();
        return SUCCESS;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
