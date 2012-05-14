project(android-external-icu4c-icuuc)

include_directories(. ../i18n)
add_definitions(-DU_COMMON_IMPLEMENTATION)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wno-unused-but-set-variable -Wno-switch -Wno-enum-compare")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wno-unused-but-set-variable -Wno-parentheses -Wno-unused-function -Wno-deprecated-declarations")

set(SRC
  cmemory.c cstring.c cwchar.c locmap.c punycode.c
  putil.c uarrsort.c ubidi.c ubidiln.c ubidi_props.c
  ubidiwrt.c ucase.c ucasemap.c ucat.c uchar.c
  ucln_cmn.c ucmndata.c ucnv2022.c ucnv_bld.c
  ucnvbocu.c ucnv.c ucnv_cb.c ucnv_cnv.c ucnvdisp.c
  ucnv_err.c ucnv_ext.c ucnvhz.c ucnv_io.c ucnvisci.c
  ucnvlat1.c ucnv_lmb.c ucnvmbcs.c ucnvscsu.c
  ucnv_set.c ucnv_u16.c ucnv_u32.c ucnv_u7.c ucnv_u8.c
  udatamem.c udataswp.c uenum.c uhash.c uinit.c
  uinvchar.c uloc.c umapfile.c umath.c umutex.c
  unames.c unorm_it.c uresbund.c ures_cnv.c uresdata.c
  usc_impl.c uscript.c ushape.c ustrcase.c ustr_cnv.c
  ustrfmt.c ustring.c ustrtrns.c ustr_wcs.c utf_impl.c
  utrace.c utrie.c utypes.c wintz.c utrie2_builder.c
  icuplug.c propsvec.c ulist.c uloc_tag.c
  bmpset.cpp unisetspan.cpp brkeng.cpp brkiter.cpp
  caniter.cpp chariter.cpp dictbe.cpp locbased.cpp
  locid.cpp locutil.cpp normlzr.cpp parsepos.cpp
  propname.cpp rbbi.cpp rbbidata.cpp rbbinode.cpp
  rbbirb.cpp rbbiscan.cpp rbbisetb.cpp rbbistbl.cpp
  rbbitblb.cpp resbund_cnv.cpp resbund.cpp ruleiter.cpp
  schriter.cpp serv.cpp servlk.cpp servlkf.cpp servls.cpp
  servnotf.cpp servrbf.cpp servslkf.cpp triedict.cpp
  ubrk.cpp uchriter.cpp uhash_us.cpp uidna.cpp uiter.cpp
  unifilt.cpp unifunct.cpp uniset.cpp uniset_props.cpp
  unistr_case.cpp unistr_cnv.cpp unistr.cpp
  unistr_props.cpp unormcmp.cpp unorm.cpp uobject.cpp
  uset.cpp usetiter.cpp uset_props.cpp usprep.cpp
  ustack.cpp ustrenum.cpp utext.cpp util.cpp util_props.cpp
  uvector.cpp uvectr32.cpp errorcode.cpp bytestream.cpp
  stringpiece.cpp mutex.cpp dtintrv.cpp ucnvsel.cpp
  uvectr64.cpp locavailable.cpp locdispnames.cpp
  loclikely.cpp locresdata.cpp normalizer2impl.cpp
  normalizer2.cpp filterednormalizer2.cpp ucol_swp.cpp
  uprops.cpp utrie2.cpp charstr.cpp uts46.cpp udata.cpp
  ../stubdata/stubdata.c
)

add_library(android-external-icu4c-icuuc OBJECT ${SRC})
set(EXTERNAL_ICU4C_COMMON_OBJECTS $<TARGET_OBJECTS:android-external-icu4c-icuuc> PARENT_SCOPE)

