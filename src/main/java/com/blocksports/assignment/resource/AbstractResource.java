package com.blocksports.assignment.resource;


import com.blocksports.assignment.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;


@Controller
@Scope(value = "prototype")
public class AbstractResource{
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_OFFSET = 20;
    @Autowired
    protected javax.servlet.http.HttpServletRequest req;

    public Pageable getPageable() {
        String page = null, offset = null, sortBy = null, order = null;
        Sort sort = null;
        if (!ObjectUtil.isStringEmptyOrNull(req.getParameter("page"))) {
            page = req.getParameter("page");
        }

        if (!ObjectUtil.isStringEmptyOrNull(req.getParameter("offset"))) {
            offset = req.getParameter("offset");
        }

        if (!ObjectUtil.isStringEmptyOrNull(req.getParameter("sortby"))) {
            sortBy = req.getParameter("sortby");
        }

        if (sortBy != null && !sortBy.isEmpty()) {
            sort = Sort.by(sortBy);
        }

        if (sort == null) {
            sort = Sort.by("createdDate");
            sort = sort.descending();
        }


        Pageable pageable = PageRequest.of(DEFAULT_PAGE, DEFAULT_OFFSET, sort);
        if (null == page && null != offset && null != sort) {
            pageable = PageRequest.of(DEFAULT_PAGE, Integer.parseInt(offset), sort);
        } else if (null != page && null == offset) {
            pageable = PageRequest.of(Integer.parseInt(page), DEFAULT_OFFSET, sort);
        } else if (null != page && null != offset) {
            pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(offset), sort);
        }
        return pageable;
    }


    public Pageable getPageableWithoutSort() {
        String page = null, offset = null, sortBy = null, order = null;
        if (ObjectUtil.isStringEmptyOrNull(req.getHeader("page"))) {
            page = req.getHeader("page");
        }
        if (ObjectUtil.isStringEmptyOrNull(req.getHeader("offset"))) {
            offset = req.getHeader("offset");
        }


        Pageable pageable = PageRequest.of(DEFAULT_PAGE, DEFAULT_OFFSET);
        if (null == page && null != offset) {
            pageable = PageRequest.of(DEFAULT_PAGE, Integer.parseInt(offset));
        } else if (null != page && null == offset) {
            pageable = PageRequest.of(Integer.parseInt(page), DEFAULT_OFFSET);
        } else if (null != page) {
            pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(offset));
        }
        return pageable;
    }



}
