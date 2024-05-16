package com.hexaware.recruitment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.recruitment.dto.ResumeInfo;

import io.jsonwebtoken.io.IOException;

@Service
public class ResumeParserService {
	
	Logger log = LoggerFactory.getLogger(ResumeParserService.class);

    @Value("${resume.parser.api.key}")
    private String apiKey;

    public ResumeInfo parseResume(MultipartFile file) throws IOException, java.io.IOException {
        String apiUrl = "https://api.apilayer.com/resume_parser/upload";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("apikey", apiKey);

        byte[] fileBytes = file.getBytes();
        HttpEntity<byte[]> entity = new HttpEntity<>(fileBytes, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
        log.info("Response From RestTemplate: "+response);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.getBody());

        ResumeInfo resumeInfo = new ResumeInfo();
        if (rootNode.has("email")) {
        	log.info("Email field is available inside rootNode.");
            resumeInfo.setEmail(rootNode.get("email").asText());
        }
        if (rootNode.has("name")) {
        	log.info("Name field is available inside rootNode.");
            resumeInfo.setName(rootNode.get("name").asText());
        }
        if (rootNode.has("phone")) {
        	log.info("Phone field is available inside rootNode.");
            resumeInfo.setPhone(rootNode.get("phone").asText());
        }
        if (rootNode.has("skills")) {
        	log.info("Skill field is available inside rootNode.");
            resumeInfo.setSkills(rootNode.get("skills").asText());
        }

        // Concatenate education and experience into strings
        StringBuilder educationBuilder = new StringBuilder();
        if(rootNode.has("education")) {
        	log.info("Education field is available inside rootNode.");
        	for (JsonNode educationNode : rootNode.get("education")) {
        		if(educationNode.has("name")) {
        			log.info("Name field is available inside educationNode.");
        			educationBuilder.append(educationNode.get("name").asText());
                    educationBuilder.append(", ");
        		}
            }
        }
        resumeInfo.setEducation(educationBuilder.toString());

        StringBuilder experienceBuilder = new StringBuilder();
        if(rootNode.has("experience")) {
        	log.info("Experience field is available");
        	for (JsonNode experienceNode : rootNode.get("experience")) {
                if(experienceNode.has("name")) {
                	log.info("Name field inside Experience field is available");
                	experienceBuilder.append(experienceNode.get("name").asText());
                }
                experienceBuilder.append(", ");
            }
        }
        resumeInfo.setExperience(experienceBuilder.toString());

        return resumeInfo;
    }
}

