project(android-external-icu4c)

add_definitions(-DICU_NO_USER_DATA_OVERRIDE)

add_subdirectory(common)
add_subdirectory(i18n)
set(EXTERNAL_ICU4C_OBJECTS
  ${EXTERNAL_ICU4C_COMMON_OBJECTS}
  ${EXTERNAL_ICU4C_I18N_OBJECTS}
  PARENT_SCOPE
)

