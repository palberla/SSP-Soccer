package de.hofuniversity.ejbbean.bean.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.hofuniversity.ejbbean.bean.TestRemote;

@Stateless(name = TestRemote.MAPPED_NAME, mappedName = TestRemote.MAPPED_NAME)
public class TestBean implements TestRemote {
    
    public TestBean() {}
    
    public String getExmapleString()
    {
	return "Example";
    }
    
    public List<String> getExampleList()
    {
	List<String> exampleList = new ArrayList<String>();
	
	exampleList.add("E");
	exampleList.add("x");
	exampleList.add("a");
	exampleList.add("m");
	exampleList.add("p");
	exampleList.add("l");
	exampleList.add("e");
	
	return exampleList;
    }
}
