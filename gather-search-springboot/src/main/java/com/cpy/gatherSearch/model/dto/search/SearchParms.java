package com.cpy.gatherSearch.model.dto.search;

import lombok.Data;
import org.jsoup.internal.FieldsAreNonnullByDefault;

import java.io.Serializable;

/**
 * @Author:成希德
 */
@Data
public class SearchParms implements Serializable {
    private String searchValue;
    private String type;
}
