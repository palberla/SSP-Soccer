package de.hofuniversity.ejbbean.bean;

import java.util.List;

import javax.ejb.Remote;

/**
 * 
 * @author Michael Jahn
 *
 */

@Remote
public interface TestRemote {
    public final String MAPPED_NAME = "ejb/Test";
    
    public String getExmapleString();
    
    public List<String> getExampleList();
}
