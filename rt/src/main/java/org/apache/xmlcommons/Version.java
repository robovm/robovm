/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package org.apache.xmlcommons;

/**
 * <meta name="usage" content="general"/>
 * Administrative class to keep track of the version number of
 * xml-commons external sources releases.
 * See xml-commons/java/external/build.xml for filtering on 1.0, etc.
 */
public class Version
{

  /**
   * Get the basic version string for the current 
   * xml-commons external sources release.
   * Version String formatted like 
   * <CODE>"XmlCommons v.r"</CODE>.
   *
   * Futurework: have this read version info from jar manifest.
   *
   * @return String denoting our current version
   */
  public static String getVersion()
  {
    return getProduct()+" "+getVersionNum();
  }

  /**
   * Get just the product name.
   *
   * @return String denoting our product name
   */
  public static String getProduct()
  {
    return "XmlCommonsExternal";
  }

  /**
   * Get just the version number v.r.
   * @return String denoting our current version number
   */
  public static String getVersionNum()
  {
    return "1.4.01";
  }

  /**
   * Print the release version to the command line.
   * @param argv command line arguments, unused.
   */
  public static void main(String argv[])
  {
    System.out.println(getVersion());
  }
}
