package com.example.demo.dao;


import com.example.demo.po.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentDao extends JpaRepository<Document, Integer> {

    /**
     * 用作者Id查询相关论文
     * @param authorId 作者Id
     * @param startTime 查询开始时间
     * @param endTime 查询结束时间
     * @param pageable 分页
     * @return {@link Document}
     */
    @Query(value = "select d from Document d where d.publicationYear between ?2 and ?3 and d.id in " +
            "(select aup.documentId from AuthorPublish aup where aup.authorId in " +
            "(select au.id from Author au where au.id = ?1))")
    Page<Document> getByAuthorId(int authorId, int startTime, int endTime, Pageable pageable);

    /**
     * 查询热度最高的文章
     * @param pageRequest 分页请求
     * @return {@link Document}
     */
    @Query(value = "select d from Document d order by d.citationCount desc")
    List<Document> findTopCiteDoc(PageRequest pageRequest);

    /**
     * 使用作者名进行模糊查询相关论文
     * @param author 作者模糊名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageable 分页
     * @return {@link Document}
     */
    @Query(value = "select d from Document d where d.publicationYear between ?2 and ?3 and d.id in " +
            "(select aup.documentId from AuthorPublish aup where aup.authorId in " +
            "(select au.id from Author au where au.name like concat('%',?1,'%') ))")
    Page<Document> findByAuthor(String author, int startTime, int endTime, Pageable pageable);

    /**
     * 使用机构名模糊查询相关论文
     * @param institution 机构名
     * @param startTime 开始时间
     * @param endTim 结束时间
     * @param pageable 分页
     * @return {@link Document}
     */
    @Query(value = "select d from Document d where d.publicationYear between ?2 and ?3 and d.id in " +
            "(select afp.documentId from AffiliationPublish afp where afp.affId in " +
            "(select af.id from Affiliation af where af.name like concat('%',?1,'%') ))")
    Page<Document> findByInstitution(String institution, int startTime, int endTim, Pageable pageable);

    /**
     * 用机构Id查询相关论文
     * @param affId 作者Id
     * @param startTime 查询开始时间
     * @param endTime 查询结束时间
     * @param pageable 分页
     * @return {@link Document}
     */
    @Query(value = "select d from Document d where d.publicationYear between ?2 and ?3 and d.id in " +
            "(select afp.documentId from AffiliationPublish afp where afp.affId in " +
            "(select af.id from Affiliation af where af.id=?1))")
    Page<Document> findByAffId(int affId, int startTime, int endTime, Pageable pageable);


    /**
     * 使用会议名模糊查询相关论文
     * @param publication 会议名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageable 分页
     * @return {@link Document}
     */
    @Query(value = "select d from Document d where d.publicationYear between ?2 and ?3 and d.publication like concat('%',?1,'%')")
    Page<Document> findByPublication(String publication, int startTime, int endTime, Pageable pageable);

    /**
     * 使用关键字模糊查询相关论文
     * @param keyword 关键字
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageable 分页
     * @return {@link Document}
     */
    @Query(value = "select d from Document d where d.publicationYear between ?2 and ?3 and d.keywords like concat('%',?1,'%')")
    Page<Document> findByKeywords(String keyword, int startTime, int endTime, Pageable pageable);

    /**
     * 根据Id查询论文
     * @param id 文章Id
     * @return {@link Document}
     */
    Document findById(int id);

    /**
     * 组合查询，参数可缺省
     * @param author 作者名
     * @param affiliation 机构名
     * @param publication 会议名
     * @param keywords 关键字
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageable 分页
     * @return {@link Document}
     */
    @Query(value = "select d from Document d where d.publicationYear between ?5 and ?6 and (?1 is null or d.id in " +
            "(select aup.documentId from AuthorPublish aup where aup.authorId in " +
            "(select au.id from Author au where au.name like %?1%))) " +
            "and (?2 is null or d.id in (select afp.documentId from AffiliationPublish afp where afp.affId in " +
            "(select af.id from Affiliation af where af.name like %?2%))) " +
            "and (?3 is null or d.publication like %?3%) " +
            "and (?4 is null or d.keywords like %?4%) ")
    Page<Document> comFind(String author,String affiliation, String publication, String keywords, int startTime, int endTime, Pageable pageable);

    /**
     * 根据作者Id查询其全部论文，无分页
     * @param authorId 作者Id
     * @return {@link Document}
     */
    @Query(value = "select * from document d where d.id in " +
            "(select aup.document_id from author_publish aup where aup.author_id = ?1) limit 3",nativeQuery = true)
    List<Document> findByAuthorId(int authorId);

    /**
     * 判断论文链接是否存在
     * @param pdfLink pdf
     * @return boolean
     */
    boolean existsByPdfLink(String pdfLink);

    /**
     * 查询全部关键词
     * @return {@link List<String>}
     */
    @Query("select d.keywords from Document d")
    List<String> findAllKeywords();

}
