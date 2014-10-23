<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.comet.core.utils.SpringUtils" %>
<%@page import="com.hazelcast.client.HazelcastClient" %>
<%@ page import="com.hazelcast.core.IMap" %>
<%@ page import="com.hazelcast.query.EntryKeyObject" %>
<%@ page import="com.hazelcast.query.Predicates" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%
    HazelcastClient client = (HazelcastClient)SpringUtils.getBean("client");
    Map map = client.getMap("map");

    map.put("aaa", "测试值得");
    map.put("aaa1", "测试值得1");
    map.put("aaa2", "测试值得2");
    map.put("aaa3", "测试值得3");

    IMap map2 = client.getMap("map");

    EntryKeyObject key = new EntryKeyObject();

    Set set = map2.entrySet(new Predicates.LikePredicate(key, "00-00-01-22%"));

    out.println("-------------" + set.size());
    out.println("-------------" + map2.get("aaa"));
%>