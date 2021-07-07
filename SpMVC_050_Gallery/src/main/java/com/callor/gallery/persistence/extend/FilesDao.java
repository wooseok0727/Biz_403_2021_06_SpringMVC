package com.callor.gallery.persistence.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.callor.gallery.model.FilesDTO;
import com.callor.gallery.persistence.GenericDao;

public interface FilesDao extends GenericDao<FilesDTO, String> {

	public int insertOrUpdate(FilesDao fileDao);
	public int insertWithList(@Param("filesList") List<FilesDTO> filesList);
	public int insertOrUpdateWithList(@Param("filesList") List<FilesDTO> filesList);
}
