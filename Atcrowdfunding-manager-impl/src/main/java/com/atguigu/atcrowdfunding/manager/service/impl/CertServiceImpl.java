package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.bean.TAccountTypeCert;
import com.atguigu.atcrowdfunding.bean.TAccountTypeCertExample;
import com.atguigu.atcrowdfunding.bean.TAccountTypeCertExample.Criteria;
import com.atguigu.atcrowdfunding.manager.dao.TAccountTypeCertMapper;
import com.atguigu.atcrowdfunding.manager.dao.TCertMapper;
import com.atguigu.atcrowdfunding.manager.service.CertService;

@Service
public class CertServiceImpl implements CertService {
	
	@Autowired
	private TCertMapper tCertMapper;
	@Autowired
	private TAccountTypeCertMapper tAccountTypeCertMapper;
	

	@Override
	public List<Cert> queryCerts() {
		return tCertMapper.selectByExample(null);
	}

	@Override
	public void deleteCert(Integer id) {

		tCertMapper.deleteByPrimaryKey(id);

	}

	@Override
	public void insertCert(Cert cert) {

		tCertMapper.insert(cert);

	}

	@Override
	public Cert queryOne(Integer id) {
		
		return tCertMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateCert(Cert cert) {
		
		tCertMapper.updateByPrimaryKey(cert);
		
	}

	@Override
	public List<TAccountTypeCert> queryCertTypeList() {
		
		return tAccountTypeCertMapper.selectByExample(null);
	}

	@Override
	public void insertCerttype(Integer accttype, Integer certid) {
		TAccountTypeCert atc = new TAccountTypeCert();
		atc.setAccttype(accttype.toString());
		atc.setCertid(certid);
		tAccountTypeCertMapper.insert(atc);
	}

	@Override
	public void deleteCerttype(Integer accttype, Integer certid) {
		TAccountTypeCertExample example = new TAccountTypeCertExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccttypeEqualTo(accttype.toString());
		criteria.andCertidEqualTo(certid);
		tAccountTypeCertMapper.deleteByExample(example);
		
	}

	@Override
	public List<Cert> queryCertByType(String accttype) {
		//先查询出资质的id
		TAccountTypeCertExample example = new TAccountTypeCertExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccttypeEqualTo(accttype);
		List<TAccountTypeCert> list = tAccountTypeCertMapper.selectByExample(example);
		List<Cert> certs = new ArrayList<Cert>();
		for (TAccountTypeCert tAccountTypeCert : list) {
			Cert cert = tCertMapper.selectByPrimaryKey(tAccountTypeCert.getCertid());
			certs.add(cert);
		}
		return certs;
	}


	@Override
	public void insertMemberCert(MemberCert tMemberCert) {
		
		tCertMapper.insertMemberCert(tMemberCert);
		
	}

	@Override
	public List<MemberCert> selectMemberCert(Integer memberid) {
		
		return tCertMapper.selectMemberCert(memberid);
	}

}
