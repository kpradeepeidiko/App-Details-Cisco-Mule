package com.eidiko.monitoring.alerts.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.eidiko.monitoring.alerts.model.AppFileName;
import com.eidiko.monitoring.alerts.model.AppFileSystem;
import com.eidiko.monitoring.alerts.model.FileSystem;
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.services.AppFileSystemServices;

@Controller
public class FileSystemContoller {

	public static final Logger logger = Logger
			.getLogger(FileSystemContoller.class);

	@Autowired
	AppFileSystemServices appfilesystemService;

	public FileSystem getFileSystemFromJson(JSONObject jsonObject) {
		logger.info("entered");
		FileSystem appfilesystem = new FileSystem();
		String fsName = jsonObject.getString("fsName");
		String fsHost = jsonObject.getString("fshost");
		String fsMount = jsonObject.getString("fsMount");
		String fsPath = jsonObject.getString("fsPath");
		appfilesystem.setFsName(fsName);
		appfilesystem.setFsHost(fsHost);
		appfilesystem.setFsMount(fsMount);
		appfilesystem.setFsPath(fsPath);
		logger.debug("AppFileSystem" + ":" + appfilesystem);
		logger.info("exiting");
		return appfilesystem;
	}

	@RequestMapping(value = "addFileSystem", method = RequestMethod.POST)
	public @ResponseBody Status AppFileSystem(@RequestBody FileSystem FileSystem) {
		logger.info("addFileSystem entered");

		if (!validateFileSystem(FileSystem)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
		logger.debug("FileSystem" + ":" + FileSystem);
		try {
			appfilesystemService.addFileSystem(FileSystem);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_ADDED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
	}

	@RequestMapping(value = "deleteFileSystem/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteFileSystem(@PathVariable("id") long id) {
		logger.info("deleteFileSystem entered");
		logger.debug("Delete deleteFileSystem By Id" + ":" + id);
		try {
			appfilesystemService.deleteFileSystem(id);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "updateFileSystem", method = RequestMethod.POST)
	public @ResponseBody Status updateFileSystem(
			@RequestBody FileSystem FileSystem) {
		logger.info("updateFileSystem entered");

		if (!validateFileSystem(FileSystem)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

		logger.debug("FileSystem :" + FileSystem);

		try {
			if (FileSystem.getId() == 0) {
				appfilesystemService.addFileSystem(FileSystem);
			} else {
				appfilesystemService.updateFileSystem(FileSystem);
			}

			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_MODIFIED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "fileSystemList", method = RequestMethod.GET)
	public @ResponseBody List<FileSystem> getFileSystem() {
		logger.info("filesystemList entered");

		List<FileSystem> fileSystemList = new ArrayList<FileSystem>();
		try {
			fileSystemList = appfilesystemService.getappFileSystemList();
			logger.debug("FileSystem List :" + fileSystemList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		logger.info("exiting");
		return fileSystemList;
	}

	@RequestMapping(value = "getFileSystem/{id}", method = RequestMethod.GET)
	public @ResponseBody FileSystem getFileSystemById(
			@PathVariable("id") long id) throws Exception {
		logger.info("getFileSystemById entered");
		logger.debug("getFilesystem By Id " + id);
		FileSystem fileSystemById = null;
		try {

			fileSystemById = appfilesystemService.getFileSystemById(id);
			logger.debug("FileSytem :" + fileSystemById);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		logger.info("exiting");
		return fileSystemById;
	}

	@RequestMapping(value = "appFileSystems/{id}", method = RequestMethod.GET)
	public @ResponseBody List<AppFileSystem> getFileSystemDetails(
			@PathVariable("id") long id) throws Exception {
		logger.info("getFileSystemByMapperAppId entered");
		logger.debug("getFileSystemByMapperAppId" + ":" + id);
		List<AppFileSystem> fileSystemList = new ArrayList<AppFileSystem>();
		try {
			fileSystemList = appfilesystemService.getFileSystemDetails(id);
			logger.debug("App File System List :" + fileSystemList);
		}

		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		logger.info("exiting");
		return fileSystemList;

	}

	@RequestMapping(value = "fileNamesList", method = RequestMethod.GET)
	public @ResponseBody List<AppFileName> getFileNames() {
		logger.info("filesystemList entered");

		List<AppFileName> fileNameList = new ArrayList<AppFileName>();
		try {
			fileNameList = appfilesystemService.getFileNameList();
			logger.debug("FileSystem List :" + fileNameList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		logger.info("exiting");
		return fileNameList;
	}

	@RequestMapping(value = "deleteFileSystemDetails/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteFSDetails(@PathVariable("id") long id) {
		logger.info("deleteFileSystem entered " + id);
		try {
			boolean result = appfilesystemService.deleteFileSystemDetails(id);
			logger.info("exiting");
			if (result) {
				return Status.getSuccesStatus(Messages.DATA_DELETE);
			} else {
				return Status.getFailureStatus(Messages.INVALID_DATA);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	public boolean validateFileSystem(FileSystem appfilesystem) {
		logger.info("entered");
		logger.debug("AppFileSystem" + ":" + appfilesystem);
		if (StringUtils.isBlank(appfilesystem.getFsName())) {
			logger.debug("Name is empty or null");
			return false;
		} else if (StringUtils.isBlank(appfilesystem.getFsHost())) {
			logger.debug("Host name  is empty or null");
			return false;
		} else if (StringUtils.isBlank(appfilesystem.getFsMount())) {
			logger.debug("FsMount is empty or null");
			return false;
		}

		else if (StringUtils.isBlank(appfilesystem.getFsPath())) {
			logger.debug("fspath is empty or null");
			return false;
		} else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;
		}

	}

}
