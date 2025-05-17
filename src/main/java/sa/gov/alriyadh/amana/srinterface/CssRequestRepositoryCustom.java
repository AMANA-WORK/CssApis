package sa.gov.alriyadh.amana.srinterface;

import sa.gov.alriyadh.amana.entity.CssRequest;
import sa.gov.alriyadh.amana.pojo.CssRequestFilter;

import java.util.List;

public interface CssRequestRepositoryCustom {
    List<CssRequest> findRequestsByFilter(CssRequestFilter filter);
}