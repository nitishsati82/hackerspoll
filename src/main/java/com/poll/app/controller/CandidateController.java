package com.poll.app.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poll.app.data.CandidateCompData;
import com.poll.app.data.CandidateData;
import com.poll.app.data.CandidateExpertArea;
import com.poll.app.data.VoterData;
import com.poll.app.services.CandExpAreaService;
import com.poll.app.services.CandidateService;
import com.poll.app.services.VoterServices;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
	@Autowired
	CandidateService serv;
	
	@Autowired
	CandExpAreaService canServ;
	
	@Autowired
	VoterServices voter;
	
	@RequestMapping(value = "/getlist", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<CandidateData> getList() {
		List<CandidateData> list =  serv.getCandidateDataDetails();
		Collections.sort(list, CandidateData.byName);
		return list;
	}
	
	@RequestMapping(value = "/geSortList/{sortBy}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<CandidateData> getSortList(@PathVariable("sortBy") String sortBy) {
		List<CandidateData> list =  serv.getCandidateDataDetails();
		if(sortBy.equals("name")) {
			Collections.sort(list, CandidateData.byName);
			return list;
		}else if(sortBy.equals("challange")) {
			Collections.sort(list, CandidateData.byChallange);
			return list;
		}else if(sortBy.equals("explevel")) {
			Collections.sort(list, CandidateData.byExp);
			return list;
		}		
		return list;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody CandidateData save(@RequestBody CandidateData CandidateData) {
		System.out.println("called to save controller.");
		
		return serv.saveCandidate(CandidateData);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Accept=text/html")
	public @ResponseBody String update(@RequestBody CandidateData CandidateData) {
		System.out.println("called to update controller.");
		
		return serv.updateCandidateData(CandidateData);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, headers = "Accept=text/html")
	public @ResponseBody String delete(@PathVariable("id") String id) {
		System.out.println("called to delete controller.");
		return serv.deleteCandidateData(Integer.parseInt(id));
	}
	
	@RequestMapping(value = "/getAllExpertArea/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<CandidateExpertArea> getList(@PathVariable("id") String id) {
		List<CandidateExpertArea> list =  canServ.getAllExpertArea(Integer.parseInt(id));
		return list;
	}
	
	@RequestMapping(value = "/saveCandidate", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody CandidateData saveExprt(@RequestBody CandidateCompData expList) {
		CandidateData canData = expList.getCandData();
		if(canData!=null && canData.getHackername().equals("")) {
			canData.setId(-1);
			canData.setHackername("Kindly enter candidate name.");
			return canData;
		}
		CandidateData savedData = serv.saveCandidate(canData);
		if(savedData.getId()>0) {
			List<CandidateExpertArea> listData = expList.getCandidateExpertArea();
			for(int i=0;i<listData.size();i++) {
				listData.get(i).setCandidate_id(savedData.getId());
			}
			canServ.saveAll(listData);
		}
		
		return savedData;
	}
	
	@RequestMapping(value = "/updateCandidate/", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody CandidateData updateExprt(@RequestBody CandidateCompData expList) {
		CandidateData canData = expList.getCandData();
		if(canData!=null && canData.getHackername().equals("")) {
			canData.setId(-1);
			canData.setHackername("Kindly enter candidate name.");
			return canData;
		}
		String savedData = serv.updateCandidateData(canData);
		if(savedData!=null && savedData.equals("Y")) {
			List<CandidateExpertArea> list =  canServ.updateAll(expList.getCandidateExpertArea(),canData.getId());
			if(list.size()>0) canData.setId(-2);
			else canData.setId(-3);
		}
		
		return canData;
	}
	
	@RequestMapping(value = "/deleteExpt/{candId}/{id}", method = RequestMethod.POST, headers = "Accept=text/html")
	public @ResponseBody String deleteExpt(@PathVariable("candId") String candId,@PathVariable("id") String id) {
		System.out.println("called to delete controller.");
		return canServ.deleteExpt(Integer.parseInt(candId), Integer.parseInt(id));
	}
	
	@RequestMapping(value = "/vote/{id}", method = RequestMethod.GET, headers = "Accept=text/html")
	public @ResponseBody String vote(@PathVariable("id") String id,HttpServletRequest request) {
		System.out.println("called to vote controller."+request.getRemoteAddr());
		String remoteIp = request.getRemoteAddr();
		String voterIp = voter.getVoterIp(remoteIp, Integer.parseInt(id));
		VoterData data = new VoterData();
		if(voterIp!=null && voterIp.equals(remoteIp)) return "NA";
		else {
			data.setCandidate_id(Integer.parseInt(id));
			data.setVoter_ip(remoteIp);
			voter.save(data).getId();
		}
		return serv.voteToHacker(Integer.parseInt(id));
	}
	@RequestMapping(value = "/getIp", method = RequestMethod.GET, headers = "Accept=text/html")
	private static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
}
