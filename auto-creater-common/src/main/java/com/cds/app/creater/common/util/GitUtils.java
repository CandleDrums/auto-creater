/**
 * @Project auto.creater.common
 * @Package com.cds.app.creater.common.util
 * @Class GitUtils.java
 * @Date Apr 13, 2020 10:45:17 AM
 * @Copyright (c) 2020 CandleDrumS.com All Right Reserved.
 */
package com.cds.app.creater.common.util;

import java.io.File;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import com.cds.base.util.bean.CheckUtils;
import com.cds.base.util.file.FileUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description git工具
 * @Notes 未填写备注
 * @author liming
 * @Date Apr 13, 2020 10:45:17 AM
 */
@Slf4j
public class GitUtils {

    public static Repository cloneRepository(String gitUrl, String outPutPath, String userName, String passwd)
        throws Exception {
        if (CheckUtils.isEmpty(outPutPath)) {
            return null;
        }
        File localPath = new File(outPutPath);
        if (!localPath.exists() && !localPath.isDirectory()) {
            localPath.mkdir();
        }
        FileUtils.deleteFile(outPutPath);

        // then clone .setBranchesToClone(Arrays.asList("refs/heads/master"))
        log.info("开始clone： " + gitUrl + " to " + localPath);
        CloneCommand clone = Git.cloneRepository().setURI(gitUrl).setDirectory(localPath);
        // if (gitUrl.contains("ssh")) {
        // SshSessionFactory myFactory = new MySShSessionFactory();
        // myFactory.setSshKeyFilePath("C:/id_rsa");
        // SshSessionFactory.setInstance(myFactory);
        // }
        // if (gitUrl.contains("http") || gitUrl.contains("https")) {
        // UsernamePasswordCredentialsProvider user = new UsernamePasswordCredentialsProvider(userName, passwd);
        // clone.setCredentialsProvider(user);
        // }

        Git repo1 = clone.call();

        for (Ref b : repo1.branchList().setListMode(ListMode.ALL).call())
            log.info("clone分支：" + b.getName());
        repo1.close();
        // now open the created repository
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder.setGitDir(new File(localPath + "/.git")).readEnvironment()
            // scan environment GIT_DIR
            // GIT_WORK_TREE
            // variables
            .findGitDir() // scan up the file system tree
            .build();
        return repository;
    }
}
