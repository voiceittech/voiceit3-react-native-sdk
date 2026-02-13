require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-voiceit"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
                  react-native-voiceit
                   DESC
  s.homepage     = "https://github.com/voiceittech/VoiceIt3-React-Native-SDK"
  s.license      = "MIT"
  s.authors      = { "VoiceIt Technologies" => "hassan@voiceit.io" }
  s.platforms    = { :ios => "15.1" }
  s.source       = { :git => "https://github.com/voiceittech/VoiceIt3-React-Native-SDK.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,c,m,swift}"
  s.requires_arc = true

  s.dependency "React-Core"
  s.dependency "VoiceIt3-IosSDK", '~> 2.3.1'
end
