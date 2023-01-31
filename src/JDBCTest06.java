/**
 * jdbc事务是自动提交的
 * jdbc默认的事务行为自动提交
 * setAutoCommit（false）   设置取消自动提交（开启事务）
 * conn.rollback()  (回滚事务)  遇到异常就要回滚
 * conn。commit（） 手动提交数据（提交事务）
 */