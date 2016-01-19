/*
 * Copyright 2015 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.physical_web.collection;

/**
 * Metadata returned from the Physical Web Service for a single URL.
 */
public class PwsResult implements Comparable<PwsResult> {
  private String mRequestUrl;
  private String mSiteUrl;
  private String mIconUrl;
  private String mGroupId;

  /**
   * Construct a PwsResult.
   * @param requestUrl The request URL, as broadcasted by the device
   * @param siteUrl The site URL, as reported by PWS
   * @param groupId The URL group ID, as reported by PWS
   */
  PwsResult(String requestUrl, String siteUrl, String iconUrl, String groupId) {
    mRequestUrl = requestUrl;
    mSiteUrl = siteUrl;
    mIconUrl = iconUrl;
    mGroupId = groupId;
  }

  /**
   * Fetches the request URL.
   * The request URL is the query sent to the Physical Web Service and should be identical to the
   * URL broadcasted by the device.
   * @return The request URL
   */
  public String getRequestUrl() {
    return mRequestUrl;
  }

  /**
   * Fetches the site URL.
   * The site URL is returned by the Physical Web Service. It may differ from the request URL if
   * a redirector or URL shortener was used.
   * @return The site URL
   */
  public String getSiteUrl() {
    return mSiteUrl;
  }

  /**
   * Check if we have an icon URL.
   * @return whether this object has an icon URL.
   */
  public boolean hasIconUrl() {
    return mIconUrl != null;
  }

  /**
   * Fetches the icon URL.
   * The icon URL is returned by the Physical Web Service.
   * @return The icon URL
   */
  public String getIconUrl() {
    return mIconUrl;
  }

  /**
   * Check if we have a group id.
   * @return whether this object has a group id.
   */
  public boolean hasGroupId() {
    return mGroupId != null;
  }

  /**
   * Fetches the URL group ID.
   * The group ID is returned by the Physical Web Service and is used to group similar (but not
   * necessarily identical) URLs.
   * @return The URL group ID, may be null
   */
  public String getGroupId() {
    return mGroupId;
  }

  /**
   * Return a hash code for this PwsResult.
   * @return hash code
   */
  @Override
  public int hashCode() {
    int hash = 1;
    hash = hash * 31 + mRequestUrl.hashCode();
    hash = hash * 31 + mSiteUrl.hashCode();
    hash = hash * 31 + mIconUrl.hashCode();
    hash = hash * 31 + ((mGroupId == null) ? 0 : mGroupId.hashCode());
    return hash;
  }

  /**
   * Check if two PwsResults are equal.
   * @param other the PwsResult to compare to.
   * @return true if the PwsResults are equal.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other instanceof PwsResult) {
      return compareTo((PwsResult) other) == 0;
    }
    return false;
  }

  /**
   * Compare two PwsResults based on request URL alphabetical ordering, breaking ties by comparing
   * site URL and group ID.
   * @param other the PwsResult to compare to.
   * @return the comparison value.
   */
  @Override
  public int compareTo(PwsResult other) {
    if (this == other) {
      return 0;
    }

    // 1. mRequestUrl
    int compareValue = Utils.nullSafeCompare(mRequestUrl, other.mRequestUrl);
    if (compareValue != 0) {
      return compareValue;
    }

    // 2. mSiteUrl
    compareValue = Utils.nullSafeCompare(mSiteUrl, other.mSiteUrl);
    if (compareValue != 0) {
      return compareValue;
    }

    // 3. mIconUrl
    compareValue = Utils.nullSafeCompare(mIconUrl, other.mIconUrl);
    if (compareValue != 0) {
      return compareValue;
    }

    // 4. mGroupId
    return Utils.nullSafeCompare(mGroupId, other.mGroupId);
  }
}
