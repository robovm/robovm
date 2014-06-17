project(android-external-openssl-ssl)

include_directories(.. ../include ../crypto)

set(SRC
  bio_ssl.c
  d1_both.c
  d1_enc.c
  d1_lib.c
  d1_pkt.c
  d1_srtp.c
  kssl.c
  s23_clnt.c
  s23_lib.c
  s23_meth.c
  s23_pkt.c
  s23_srvr.c
  s2_clnt.c
  s2_enc.c
  s2_lib.c
  s2_meth.c
  s2_pkt.c
  s2_srvr.c
  s3_both.c
  s3_cbc.c
  s3_clnt.c
  s3_enc.c
  s3_lib.c
  s3_meth.c
  s3_pkt.c
  s3_srvr.c
  ssl_algs.c
  ssl_asn1.c
  ssl_cert.c
  ssl_ciph.c
  ssl_err.c
  ssl_err2.c
  ssl_lib.c
  ssl_rsa.c
  ssl_sess.c
  ssl_stat.c
  ssl_txt.c
  t1_clnt.c
  t1_enc.c
  t1_lib.c
  t1_meth.c
  t1_reneg.c
  t1_srvr.c
  tls_srp.c
)

add_library(android-external-openssl-ssl OBJECT ${SRC})
set(EXTERNAL_OPENSSL_SSL_OBJECTS $<TARGET_OBJECTS:android-external-openssl-ssl> PARENT_SCOPE)

