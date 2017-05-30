package com.eidiko.monitoring.alerts.controller;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.eidiko.monitoring.alerts.model.FileSystemAppMapper;
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.services.FileSystemMapperServices;

@Controller
public class FileSystemAppMapperController {
	public static final Logger logger = Logger
			.getLogger(FileSystemAppMapperController.class);
	@Autowired
	FileSystemMapperServices fileSystemMappingServices;

	public FileSystemAppMapper getFileSystemMapperFromJson(JSONObject jsonObject) {
		logger.info("entered");
		FileSystemAppMapper fileSystemMapper = new FileSystemAppMapper();

		long appFSId = Long.parseLong(jsonObject.getString("appFSId"));
		long appApplicationId = Long.parseLong(jsonObject
				.getString("appApplicationId"));

		fileSystemMapper.setAppFSId(appFSId);
		fileSystemMapper.setAppApplicationId(appApplicationId);
		logger.debug("Application FileSystem Mapper" + ":" + fileSystemMapper);
		logger.info("exiting");
		return fileSystemMapper;
	}

	@RequestMapping(value = "/appFileSystemMapper", method = RequestMethod.POST)
	public @ResponseBody Status addApplicationMapper(@RequestBody String values) {
		logger.info("addApplicationFileSystemMapper entered");
		JSONObject js = JSONObject.fromObject(values);
		long appApplicationId = Long
				.parseLong(js.getString("appApplicationId"));
		long appFSId;
		JSONArray appFSData = js.getJSONArray("appFSId");
		logger.debug("appFSData" + appFSData);
		for (int i = 0; i < appFSData.size(); i++) {
			FileSystemAppMapper fileSystemMapper = new FileSystemAppMapper();
			appFSId = Long.parseLong(appFSData.getString(i));
			fileSystemMapper.setAppFSId(appFSId);
			fileSystemMapper.setAppApplicationId(appApplicationId);
			if (!validateFileSystemAppMapper(fileSystemMapper)) {
				return Status.getFailureStatus(Messages.EXCEPTION);
			}

			logger.debug("applicationMapper" + fileSystemMapper);
			try {
				fileSystemMappingServices
						.addFileSystemAppMapper(fileSystemMapper);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				return Status.getFailureStatus(Messages.EXCEPTION);
			}
		}
		logger.info("exiting");
		return Status.getSuccesStatus(Messages.DATA_ADDED);
	}

	@RequestMapping(value = "deleteApplicationFileSystemMapper/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteApplicationFileSystemMapper(
			@PathVariable("id") long id) {
		logger.info("entered");
		logger.debug("Deleting ApplicationFileSystemMapper" + ":" + id);
		try {
			fileSystemMappingServices.deleteFileSystemMapper(id);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
	}

	@RequestMapping(value = "/appFileSystemMapperList", method = RequestMethod.PUT)
	public @ResponseBody List<FileSystemAppMapper> getApplicationMapper() {
		logger.info("entered");
		List<FileSystemAppMapper> appFileSystemMapperList = new ArrayList<FileSystemAppMapper>();
		try {
			appFileSystemMapperList = fileSystemMappingServices
					.getAppFileSystemMapperList();
			logger.debug("Application FileSystem MapperList" + ":"
					+ appFileSystemMapperList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return appFileSystemMapperList;
	}

	@RequestMapping(value = "/updateAppFileSystemMapper", method = RequestMethod.POST)
	public @ResponseBody Status updateAppFileSystemMapper(
			@RequestBody String values) {
		logger.info("entered");
		JSONObject js = JSONObject.fromObject(values);

		FileSystemAppMapper fileSystemMapper = new FileSystemAppMapper();

		fileSystemMapper = getFileSystemMapperFromJson(js);

		if (!validateFileSystemAppMapper(fileSystemMapper)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
		logger.debug("Application FileSystem Mapper" + ":" + fileSystemMapper);
		int id = Integer.parseInt(js.getString("id"));
		fileSystemMapper.setId(id);
		try {
			if (fileSystemMapper.getId() == 0) {
				fileSystemMappingServices
						.addAppFileSystemMapper(fileSystemMapper);
			} else {
				fileSystemMappingServices
						.updateAppFileSystemMapper(fileSystemMapper);
			}
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_MODIFIED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
	}

	@RequestMapping(value = "getAppFileSystemMapper/{id}", method = RequestMethod.PUT)
	public @ResponseBody FileSystemAppMapper getAppFileSystemMapperById(
			@PathVariable("id") long id) throws Exception {
		logger.info("getAppFileSystemMapperById entered");
		logger.debug("getAppFileSystemMapper" + ":" + id);
		FileSystemAppMapper getAppFileSystemMapperById = null;
		try {
			long id1 = id;
			getAppFileSystemMapperById = fileSystemMappingServices
					.getAppFileSystemMapperById(id1);
			logger.debug(getAppFileSystemMapperById);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return getAppFileSystemMapperById;
	}

	public boolean validateFileSystemAppMapper(FileSystemAppMapper appFSMapper) {
		logger.info("entered");
		logger.debug("ApplicationMapper" + ":" + appFSMapper);
		if (StringUtils.isBlank(String.valueOf(appFSMapper.getAppFSId()))) {
			logger.debug("FileSystem Id is empty or null");
			return false;
		} else if (StringUtils.isBlank(String.valueOf(appFSMapper
				.getAppApplicationId()))) {
			logger.debug("Application Id is empty or null");
			return false;
		} else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;
		}
	}
}
