Select distinct mq.APP_MQ_EVENT_ID,mq.mq_object_type,mq.mq_object_name,mq.mq_qmgr_name,mq.mq_object_status,mq.mq_object_date,
mq.mq_event_hostname,mq.mq_event_inserted_date,mq.MQ_REASON_CODE,
amp.EVENT_LEVEL,amp.EVENT_DESCRIPTION
from qmon.app_mq_event mq,
qmon.app_mqevent_properties amp,
qmon.app_qmgr q,
qmon.app_qmgr_app_mapper qa,
qmon.app_mq_objects ao
where amp.REASON_CODE=mq.MQ_REASON_CODE
AND
qa.app_qmgr_id=q.APP_QMGR_ID
AND
qa.app_application_id=1
AND
ao.app_qmgr_app_application_id=qa.APP_QMGR_APP_MAPPER_ID
AND
qa.APP_QMGR_APP_MAPPER_ID=3
AND
mq.mq_qmgr_name='QM1'