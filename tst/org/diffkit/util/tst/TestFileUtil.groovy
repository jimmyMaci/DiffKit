/**
 * Copyright 2010 Joseph Panico
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.diffkit.util.tst



import org.apache.commons.io.FileUtils;

import org.diffkit.util.DKFileUtil;
import org.diffkit.util.DKResourceUtil;

import groovy.util.GroovyTestCase;


/**
 * @author jpanico
 */
public class TestFileUtil extends GroovyTestCase {
   
   public void testPrepend() {
      File sourceTarget = DKResourceUtil.findResourceAsFile("org/diffkit/util/tst/prepend_target.txt")
      assert sourceTarget
      File testTarget = ['./prependTest']
      FileUtils.copyFile( sourceTarget, testTarget)
      def prependString = 'prepend\nprepend\nprepend\n---\n'
      DKFileUtil.prepend( prependString, testTarget)
      
      def sourceText = FileUtils.readFileToString(sourceTarget)
      def prependedText = FileUtils.readFileToString(testTarget)
      
      assert prependedText == prependString + sourceText
   }
   
   public void testIsRelative(){
      println "separtor->${File.separator}"
      println "path->${new File('./')}"
      assert DKFileUtil.isRelative(new File("."))
      assert ! DKFileUtil.isRelative(new File(""))
      assert DKFileUtil.isRelative(new File('./'))
      assert DKFileUtil.isRelative(new File("./test/"))
      assert DKFileUtil.isRelative(new File(".\\"))
      assert ! DKFileUtil.isRelative(new File("/Users/joe/tmp"))
   }
}
