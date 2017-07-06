package de.hofuniversity.webbean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.directory.InitialDirContext;

import de.hofuniversity.ejbbean.bean.TableListRemote;
import de.hofuniversity.ejbbean.bean.impl.TableListBean;
import de.hofuniversity.ejbbean.data.TeamGroupSummaryData;


@ManagedBean
@ViewScoped
public class WebTableBean {
    
    private TableListRemote tblRemote = null;
    
    private TableListRemote thisGetTableList() {
	if (tblRemote == null) {
	    try {
		tblRemote = (TableListRemote) new InitialDirContext().lookup(TableListRemote.MAPPED_NAME);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return tblRemote;
    }
    
//    private TableListBean tbl = new TableListBean();
    
    public List<TeamGroupSummaryData> getTableList() {
	List<TeamGroupSummaryData> tableList = new ArrayList<TeamGroupSummaryData>();
	Collection<TeamGroupSummaryData> tgsdCollection = this.thisGetTableList().getTableList();
	
	tableList.addAll(tgsdCollection);
	
	return tableList;
    }
    
//    public List<String> getTeamNameList() {
//	List<String> nameList = new ArrayList<String>();
//	Collection<TeamGroupSummaryData> tgsdCollection = tbl.getTableList();
//	
//	for (TeamGroupSummaryData tgsd : tgsdCollection) {
//	    nameList.add(tgsd.getTeamName());
//	}
//	
//	return nameList;
//    }
//    
//    
//   public List<Integer> getTeamPoints() {
//       List<Integer> pointList = new ArrayList<Integer>();
//       Collection<TeamGroupSummaryData> tgsdCollection = tbl.getTableList();
//       for (TeamGroupSummaryData tgsd : tgsdCollection) {
//	   pointList.add(tgsd.getPoints());
//       }
//       return pointList;
//   }

}
