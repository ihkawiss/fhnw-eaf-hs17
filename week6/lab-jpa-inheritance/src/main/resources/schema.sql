-- Muss auf dem Klassenpfad liegen, wird ausgeführt falls
-- spring.datasource.initialize=true
-- spring.jpa.hibernate.ddl-auto=none (sonst überschreibt generiertes Schema das hier definierte)
 
CREATE TABLE topic (
	ID BIGINT(19) auto_increment primary key,
	DTYPE VARCHAR(31) NOT NULL,
	OWNER VARCHAR(155) NOT NULL,
	TITLE VARCHAR(155) NOT NULL,
	CONTENT VARCHAR(155),
	VALID_UNTIL DATE(8),
);

ALTER TABLE Topic ADD CONSTRAINT post_content_check CHECK (
	CASE WHEN DTYPE = 'Post'
	THEN CASE WHEN content IS NOT NULL THEN 1 ELSE 0 END
	ELSE 1
	END = 1
);
