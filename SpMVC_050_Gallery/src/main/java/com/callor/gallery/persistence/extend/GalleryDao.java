package com.callor.gallery.persistence.extend;

import java.util.List;

import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.persistence.GenericDao;

public interface GalleryDao extends GenericDao<GalleryDTO, Long> {

	public List<GalleryFilesDTO> findByIdGalleryFiles(Long g_seq);
	public GalleryDTO findByIdGalleryFilesResultMap(Long g_seq);
}
