package com.callor.gallery.service;

import com.callor.gallery.model.PageDTO;

public interface PageService {

	public PageDTO makePagination(int totalListSize, int currentPage);
}
