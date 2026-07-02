-- 平台接口定义
CREATE TABLE platform_openapi (
    -- 编号
                               id BIGSERIAL PRIMARY KEY,
    -- 服务编号
                               service_id BIGINT NOT NULL,
    -- 接口名称
                               name VARCHAR(512) NOT NULL,
    -- Http方法
                               http_method VARCHAR(32) NOT NULL,
    -- 类方法
                               class_method VARCHAR(512) NOT NULL,
    -- uri
                               uri VARCHAR(256) DEFAULT NULL,
    -- 说明
                               remark varchar(2048) DEFAULT NULL,
    -- 审计字段：创建人编号
                               creator_id VARCHAR(64) NOT NULL,
    -- 审计字段：创建人
                               created_by VARCHAR(64) NOT NULL,
    -- 审计字段：创建时间
                               created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -- 审计字段：更新人编号
                               updater_id VARCHAR(64) DEFAULT NULL,
    -- 审计字段：更新人
                               updated_by VARCHAR(64) DEFAULT NULL,
    -- 审计字段：更新时间
                               updated_time TIMESTAMP DEFAULT NULL,
    -- 乐观锁
                               version INTEGER DEFAULT 1
);

COMMENT ON COLUMN platform_openapi.id IS '编号';
COMMENT ON COLUMN platform_openapi.service_id IS '服务编号';
COMMENT ON COLUMN platform_openapi.name IS '接口名称';
COMMENT ON COLUMN platform_openapi.http_method IS 'Http方法';
COMMENT ON COLUMN platform_openapi.class_method IS '类方法';
COMMENT ON COLUMN platform_openapi.uri IS 'URI';
COMMENT ON COLUMN platform_openapi.remark IS '说明';
COMMENT ON COLUMN platform_openapi.creator_id IS '创建人编号';
COMMENT ON COLUMN platform_openapi.created_by IS '创建人';
COMMENT ON COLUMN platform_openapi.created_time IS '创建时间';
COMMENT ON COLUMN platform_openapi.updater_id IS '更新人编号';
COMMENT ON COLUMN platform_openapi.updated_by IS '更新人';
COMMENT ON COLUMN platform_openapi.updated_time IS '更新时间';
COMMENT ON COLUMN platform_openapi.version IS '版本';

COMMENT ON TABLE platform_openapi IS '平台接口定义';
