package com.example.JOBSHOP.JOBSHOP.skills.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.skills.Skill;

import jakarta.transaction.Transactional;

@Service
public class skillServiceImpl implements skillServiceInterface{

	
	@Autowired
	private skillRepository skillRepository;
	
	@Override
	public int insert(Skill skill) {
		Skill s=skillRepository.save(skill);
		if(s!=null)
		{
			return 1;
		}else 
		{
			return 0;
		}
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		skillRepository.deleteById(id);
	}

	@Override
	public Optional<Skill> findById(Long id) {
		Optional <Skill> oSkill=skillRepository.findById(id);
		if(oSkill.isPresent())
		{
			return oSkill;
		}else 
		{
			return null;
		}
		
	}


	@Transactional
	@Override
	public void updateSkill(Long id,Skill skill) {
		
		if(findById(id)!=null)
		{
			Skill skillForUpdate=findById(id).get(); 
			if(skill.getSkillName()!=null)
			{
				skillForUpdate.setSkillName(skill.getSkillName());
			}
			skillRepository.save(skillForUpdate);
		}
		
	}

	@Override
	public Skill findByName(String skillName) {
		return skillRepository.findBySkillName(skillName);
	}

	@Override
	public List<Skill> findBySkillName(String skillName) {
		
		return skillRepository.findBySkillNameList(skillName);
	}

	@Override
	public void insertAll(List<Skill> skillsToInsert) {
	
		skillRepository.saveAll(skillsToInsert);
	}

	@Override
	public Skill insertForJobSeekerOperation(Skill skill) {
		return skillRepository.save(skill);
	}

	@Override
	public Long findIdByName(String skillName) {
		return skillRepository.findIdByName(skillName);
	}

	@Override
	public List<String> findAllDistinct() {
		return skillRepository.findAllDistinctSkills();
	}

	
}
